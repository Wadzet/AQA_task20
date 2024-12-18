package task_20.listener;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import task_20.driver.DriverProvider;
import task_20.utils.ScreenshotUtil;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AllureListener implements ITestListener, IInvokedMethodListener {
    private static final Logger logger = LogManager.getLogger(AllureListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("Starting test suite: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Finished test suite: {}", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getMethod().getMethodName(), result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            switch (testResult.getStatus()) {
                case ITestResult.FAILURE:
                    attachScreenshot("TestFailure");
                    attachPageSource("PageSource_Failure");
                    break;
                case ITestResult.SUCCESS:
                    attachScreenshot("TestSuccess");
                    attachPageSource("PageSource_Success");
                    break;
                case ITestResult.SKIP:
                    attachScreenshot("TestSkipped");
                    attachPageSource("PageSource_Skipped");
                    break;
            }
        }
    }

    private void attachScreenshot(String attachName) {
        byte[] screenshot = ScreenshotUtil.getScreenshotAsBytes(DriverProvider.getDriver());
        if (screenshot != null) {
            Allure.addAttachment(attachName, "image/png", new ByteArrayInputStream(screenshot), "png");
        }
    }

    private void attachPageSource(String attachName) {
        String pageSource = DriverProvider.getDriver().getPageSource();
        if (pageSource != null) {
            Allure.addAttachment(attachName, "text/plain",
                    new ByteArrayInputStream(pageSource.getBytes(StandardCharsets.UTF_8)), "txt");
        }
    }
}

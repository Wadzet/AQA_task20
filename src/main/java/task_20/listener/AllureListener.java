package task_20.listener;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import task_20.driver.DriverProvider;
import task_20.utils.ScreenshotUtil;

public class AllureListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(AllureListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("Starting test suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Finished test suite: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: " + result.getMethod().getMethodName());
        attachScreenshot("TestSuccess");
        attachPageSource("PageSource_Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getMethod().getMethodName(), result.getThrowable());
        attachScreenshot("TestFailure");
        attachPageSource("PageSource_Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: " + result.getMethod().getMethodName());
        attachScreenshot("TestSkipped");
        attachPageSource("PageSource_Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public byte[] attachScreenshot(String attachName) {
        return ScreenshotUtil.getScreenshotAsBytes(DriverProvider.getDriver());
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public String attachPageSource(String attachName) {
        return DriverProvider.getDriver().getPageSource();
    }
}

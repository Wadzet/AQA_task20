package task_20;

import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import task_20.bo.CalculatorBO;
import task_20.driver.DriverProvider;
import task_20.listener.AllureListener;
import task_20.utils.ScreenshotUtil;
import org.testng.annotations.Listeners;
import java.io.IOException;

@Listeners({AllureListener.class})
public class Task20CalculatorTest {

    private static final Logger logger = LogManager.getLogger(Task20CalculatorTest.class);
    private CalculatorBO calculatorBO;

    @BeforeClass
    public void setUp() {
        logger.info("Initializing driver.");
        DriverProvider.initDriver();
        calculatorBO = new CalculatorBO();
    }

    @DataProvider(name = "percentageTestData")
    public Object[][] provideTestData() {
        return new Object[][]{
                {"10", "50", "5"},
                {"20", "25", "5"},
                {"100", "10", "10"}
        };
    }

    @Test(dataProvider = "percentageTestData")
    @Description("Verify percentage calculations.")
    public void testPercentageCalculation(String input1, String input2, String expectedResult) throws IOException {
        logger.info("Starting test with input1={}, input2={}, expectedResult={}", input1, input2, expectedResult);
        String actualResult = calculatorBO.calculatePercentage(input1, input2);
        String numericResult = actualResult.replaceAll("[^0-9]", "");
        String screenshotName = String.format("test_result_%s_%s", input1, input2);
        ScreenshotUtil.captureScreenshot(DriverProvider.getDriver(), screenshotName);
        logger.info("Actual result: {}", numericResult);
        Assert.assertEquals(numericResult, expectedResult, "Incorrect calculation result");
        logger.info("Test passed successfully for input data: {}, {}", input1, input2);
    }

    @AfterClass
    public void tearDown() {
        logger.info("Closing driver.");
        DriverProvider.quitDriver();
    }
}

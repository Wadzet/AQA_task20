package task_20;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.*;
import task_20.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Task20CalculatorTest {

    private static final Logger logger = LogManager.getLogger(Task20CalculatorTest.class);

    private CalculatorPage calculatorPage;

    @BeforeClass
    public void setUp() {
        logger.info("Initializing driver and launching the app.");
        calculatorPage = new CalculatorPage();
        calculatorPage.initDriver();
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
    @Description("Verify percentage calculations using a calculator app.")
    public void testPercentageCalculation(String input1, String input2, String expectedResult) throws IOException {
        logger.info("Starting test with data: input1={}, input2={}, expectedResult={}", input1, input2, expectedResult);

        calculatorPage.enterNumber(input1);
        calculatorPage.pressPercentage();
        calculatorPage.enterNumber(input2);
        calculatorPage.pressEquals();

        String actualResult = calculatorPage.getResult();
        String numericResult = actualResult.replaceAll("[^0-9]", "");

        String screenshotName = String.format("test_result_%s_%s", input1, input2);
        ScreenshotUtil.captureScreenshot(calculatorPage.getDriver(),  screenshotName);

        logger.info("Actual result: {}", numericResult);
        Allure.step("Expected result: " + expectedResult + ", Actual result: " + numericResult);

        Assert.assertEquals(numericResult, expectedResult, "Incorrect calculation result");

        logger.info("Test passed successfully for input data: {}, {}", input1, input2);
    }



    @AfterClass
    public void tearDown() {
        logger.info("Closing driver.");
        calculatorPage.quitDriver();
    }
}

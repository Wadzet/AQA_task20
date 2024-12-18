package task_20.po;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import task_20.driver.DriverProvider;

import java.time.Duration;

public class CalculatorPO {

    private final AndroidDriver driver;
    private static final Logger logger = LogManager.getLogger(CalculatorPO.class);

    @FindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_percentage")
    private WebElement percentageElement;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal")
    private WebElement equalsElement;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/calc_edt_formula")
    private WebElement resultField;

    public CalculatorPO() {
        this.driver = DriverProvider.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @Step("Enter number {number}")
    public CalculatorPO enterNumber(String number) {
        logger.info("Entering number: {}", number);
        for (char digit : number.toCharArray()) {
            String digitId = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_0" + digit;
            WebElement digitElement = driver.findElement(By.id(digitId));
            digitElement.click();
        }
        return this;
    }

    @Step("Press percentage")
    public CalculatorPO pressPercentage() {
        logger.info("Pressing percentage button");
        percentageElement.click();
        return this;
    }

    @Step("Press equals")
    public CalculatorPO pressEquals() {
        logger.info("Pressing equals button");
        equalsElement.click();
        return this;
    }

    @Step("Get result")
    public String getResult() {
        logger.info("Getting result");
        return resultField.getText().trim();
    }
}

package task_20;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorPage {

    private AndroidDriver driver;

    public void initDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("deviceName", "RF8W80XNZLN");
        caps.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        caps.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    public void enterNumber(String number) {
        for (char digit : number.toCharArray()) {
            driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_0" + digit)).click();
        }
    }

    public void pressPercentage() {
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_percentage")).click();
    }

    public void pressEquals() {
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal")).click();
    }

    public String getResult() {
        WebElement resultField = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_edt_formula"));
        return resultField.getText().trim();
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

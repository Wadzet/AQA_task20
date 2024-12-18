package task_20.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverProvider {
    private static AndroidDriver driver;

    public static void initDriver() {
        if (driver == null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "RF8W80XNZLN");
            caps.setCapability("appPackage", "com.sec.android.app.popupcalculator");
            caps.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

            try {
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Failed to initialize driver", e);
            }
        }
    }

    public static AndroidDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

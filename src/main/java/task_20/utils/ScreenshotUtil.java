package task_20.utils;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void captureScreenshot(AndroidDriver driver, String stepName) {
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        String filePath = "logs/screenshots/" + stepName + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(filePath));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    public static byte[] getScreenshotAsBytes(AndroidDriver driver) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }
}

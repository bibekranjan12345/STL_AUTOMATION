package com.STL.Utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String destFolder = System.getProperty("user.dir") + "/test-output/ExtentReports/screenshots/";
        String destPath = destFolder + screenshotName + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dir = new File(destFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileUtils.copyFile(src, new File(destPath));
        } catch (Exception e) {
            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
            e.printStackTrace();
        }

        return "./screenshots/" + screenshotName + ".png";
    }
}

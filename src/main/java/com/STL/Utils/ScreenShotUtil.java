package com.STL.Utils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String destDir = System.getProperty("user.dir") + "/test-output/ExtentReports/screenshots/";
        File screenshotDir = new File(destDir);
        if (!screenshotDir.exists()) screenshotDir.mkdirs();

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destPath = destDir + screenshotName + ".png";
        try {
            FileUtils.copyFile(src, new File(destPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "./screenshots/" + screenshotName + ".png";
    }
}

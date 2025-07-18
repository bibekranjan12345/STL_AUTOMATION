package com.STL.Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil 
{
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save screenshot under test-output/ExtentReports/screenshots
        String destFolder = System.getProperty("user.dir") + 
                            "/test-output/ExtentReports/screenshots/";
        File dir = new File(destFolder);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String destPath = destFolder + screenshotName + ".png";
        try {
            FileUtils.copyFile(src, new File(destPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return path relative to HTML report
        return "./screenshots/" + screenshotName + ".png";
    }
}

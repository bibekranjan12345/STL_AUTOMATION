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


















//package com.STL.Utils;              2
//
//import java.io.File;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//public class ScreenShotUtil {
//    public static String captureScreenshot(WebDriver driver, String screenshotName) {
//        String destFolder = System.getProperty("user.dir") + "/test-output/ExtentReports/screenshots/";
//        String destPath = destFolder + screenshotName + ".png";
//
//        try {
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            File dir = new File(destFolder);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            FileUtils.copyFile(src, new File(destPath));
//        } catch (Exception e) {
//            System.out.println("⚠️ Screenshot capture failed: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        return "./screenshots/" + screenshotName + ".png";
//    }
//}

















//package com.STL.Utils;                1
//
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//
//public class ScreenShotUtil 
//{
//    public static String captureScreenshot(WebDriver driver, String screenshotName) {
//        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//        // Save screenshot under test-output/ExtentReports/screenshots
//        String destFolder = System.getProperty("user.dir") + 
//                            "/test-output/ExtentReports/screenshots/";
//        File dir = new File(destFolder);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        String destPath = destFolder + screenshotName + ".png";
//        try {
//            FileUtils.copyFile(src, new File(destPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Return path relative to HTML report
//        return "./screenshots/" + screenshotName + ".png";
//    }
//}

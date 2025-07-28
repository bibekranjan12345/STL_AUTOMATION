package com.STL.Base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.STL.Feature.LoginFeature;
import com.STL.Utils.ConfigReader;
import com.STL.Utils.ScreenShotUtil;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest extends SuiteSetup {

    protected WebDriver driver;
    protected ExtentTest test;

    @BeforeMethod
    public void setup(Method method) {
        driver = browserInitializationAndOpenApplication(ConfigReader.getProperty("browser"));
        test = SuiteSetup.extent.createTest(method.getName());
        new LoginFeature(driver).loginToApplication();
    }

    public WebDriver browserInitializationAndOpenApplication(String browserName) {
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new EdgeDriver(options);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }
            driver = new FirefoxDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.get(ConfigReader.getProperty("url"));
        return driver;
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (test != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                String screenshotPath = ScreenShotUtil.captureScreenshot(driver, result.getName());
                test.fail(result.getThrowable());
                test.addScreenCaptureFromPath(screenshotPath);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test Passed");
            } else {
                test.skip("Test Skipped");
            }
        }

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SuiteSetup.extent.flush();

        if (driver != null) {
            driver.quit();
        }
    }
}



























//package com.STL.Base;
//
//import java.lang.reflect.Method;
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//
//import com.STL.Feature.LoginFeature;
//import com.STL.Utils.ConfigReader;
//import com.STL.Utils.ScreenShotUtil;
//import com.aventstack.extentreports.ExtentTest;
//
//public class BaseTest extends SuiteSetup{
//
//    protected WebDriver driver;
//    protected ExtentTest test;
//
//    @BeforeMethod
//    public void setup(Method method) {
//        driver = browserInitializationAndOpenApplication(ConfigReader.getProperty("browser"));
//        
//        // Use static extent object from SuiteSetup
//        test = SuiteSetup.extent.createTest(method.getName());
//        
//        new LoginFeature(driver).loginToApplication();
//    }
//
//    public WebDriver browserInitializationAndOpenApplication(String browserName) {
//        if (browserName.equalsIgnoreCase("chrome")) {
//            driver = new ChromeDriver();
//        } else if (browserName.equalsIgnoreCase("edge")) {
//            driver = new EdgeDriver();
//        } else if (browserName.equalsIgnoreCase("firefox")) {
//            driver = new FirefoxDriver();
//        }
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
//        driver.get(ConfigReader.getProperty("url"));
//        return driver;
//    }
//
//    @AfterMethod
//    public void teardown(ITestResult result) {
//        if (test != null)
//        {
//            if (result.getStatus() == ITestResult.FAILURE) {
//                String screenshotPath = ScreenShotUtil.captureScreenshot(driver, result.getName());
//                test.fail(result.getThrowable());
//                test.addScreenCaptureFromPath(screenshotPath);
//            } else if (result.getStatus() == ITestResult.SUCCESS) {
//                test.pass("Test Passed");
//            } else {
//                test.skip("Test Skipped");
//            }
//        }
//        
//        try
//        {
//        	Thread.sleep(3000);
//        }
//        catch(Exception e)
//        {
//        	e.printStackTrace();
//        }
//        SuiteSetup.extent.flush();
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}

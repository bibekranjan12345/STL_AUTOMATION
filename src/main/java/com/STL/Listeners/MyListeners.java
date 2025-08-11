package com.STL.Listeners;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.STL.Utils.ExtentReporter;
import com.STL.Utils.ScreenShotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private String testName;
    private static ExtentReports extentReport;

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    private static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.getExtent();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        ExtentTest test = extentReport.createTest(testName);
        test.log(Status.INFO, "Test Started: " + testName);
        setExtentTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getExtentTest().log(Status.PASS, "Test Passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        getExtentTest().log(Status.FAIL, "Test Failed: " + testName);
        getExtentTest().fail(result.getThrowable());

        try {
            WebDriver driver = extractDriverFromTestInstance(result);
            if (driver != null) {
                String screenshotPath = ScreenShotUtil.captureScreenshot(driver, testName);
                getExtentTest().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            } else {
                System.out.println("Driver is null. Screenshot not taken.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        getExtentTest().log(Status.SKIP, "Test Skipped: " + testName);
        if (result.getThrowable() != null) {
            getExtentTest().log(Status.INFO, result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReport != null) {
            extentReport.flush();
        }
    }

    // Extract driver field from test instance (search in superclasses too)
    private WebDriver extractDriverFromTestInstance(ITestResult result) throws Exception {
        Object testInstance = result.getInstance();
        Class<?> clazz = testInstance.getClass();

        while (clazz != null) {
            try {
                Field driverField = clazz.getDeclaredField("driver");
                driverField.setAccessible(true);
                return (WebDriver) driverField.get(testInstance);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}

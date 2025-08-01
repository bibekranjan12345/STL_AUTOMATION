package com.STL.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

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
	
	private String reportFilePath;
    private static ExtentReports extentReport;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private String testName;

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    @Override
    public void onStart(ITestContext context) {
        String suiteName = context.getSuite().getName(); // Dynamically fetch suite name
        extentReport = ExtentReporter.generateExtentReport(suiteName);
        
        String sanitizedSuiteName = suiteName.replaceAll("\\s+", "");
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new java.util.Date());
        reportFilePath = System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport_" + sanitizedSuiteName + "_" + timestamp + ".html";
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

        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass()
                    .getRealClass()
                    .getDeclaredField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            String screenshotPath = ScreenShotUtil.captureScreenshot(driver, testName);
            try {
                getExtentTest().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        extentReport.flush();
        try {
            File reportFile = new File(reportFilePath);
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

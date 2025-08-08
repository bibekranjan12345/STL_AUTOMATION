package com.STL.Listeners;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.STL.Base.SuiteSetup;
import com.STL.Utils.ExtentReporter;
import com.STL.Utils.ScreenShotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener {

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
    	 extentReport = SuiteSetup.extent;
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
            Object testInstance = result.getInstance();
            Class<?> clazz = testInstance.getClass();

            WebDriver driver = null;
            try {
                Field driverField = findField(clazz, "driver"); // your helper
                driverField.setAccessible(true);
                driver = (WebDriver) driverField.get(testInstance);
            } catch (NoSuchFieldException nf) {
                System.out.println("No 'driver' field found in test class or parent classes. Skipping screenshot.");
            }

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

            if (ExtentReporter.generatedReportPath != null) {
                File reportFile = new File(ExtentReporter.generatedReportPath);

                String suiteName = context.getSuite().getName().replaceAll(" ", "") + "Suite";
                File staticReportFile = new File("test-output/ExtentReports/ExtentReport_" + suiteName + ".html");

                try {
                    Files.copy(reportFile.toPath(), staticReportFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied report to: " + staticReportFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ExtentReporter.generatedReportPath is null. Report copy skipped.");
            }
        }
    }

 // Helper method to search for a field in the class hierarchy
    private Field findField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass(); // Move up the inheritance chain
            }
        }
        throw new NoSuchFieldException("Field '" + fieldName + "' not found in class hierarchy.");
    }

}

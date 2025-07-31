package com.STL.Base;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.STL.Utils.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;

public class SuiteSetup implements ISuiteListener {
    public static ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        String suiteName = suite.getName();  // e.g. "Smoke Suite"
        System.out.println("=== Initializing ExtentReports for: " + suiteName + " ===");
        extent = ExtentReporter.generateExtentReport(suiteName);
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
            System.out.println("=== Extent Report Flushed Successfully ===");
        }
    }
}

package com.STL.Base;

import org.testng.annotations.BeforeSuite;

import com.STL.Utils.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;

public class SuiteSetup 
{
	public static ExtentReports extent;

    @BeforeSuite
    public void initializeExtent() {
        System.out.println("=== Executing SuiteSetup: Initializing ExtentReports ===");
        extent = ExtentReporter.generateExtentReport();
    }

}

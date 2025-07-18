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

public class MyListeners implements ITestListener //Override the methods of ITestListener interface to MyListeners
{
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;

	@Override
	public void onStart(ITestContext context) 
	{
		extentReport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		testName=result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" start executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.log(Status.PASS, testName+" successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		WebDriver driver = null;

		try
		{
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			e.printStackTrace();
		}

		if (driver != null) 
		{
			String screenshotPath = ScreenShotUtil.captureScreenshot(driver, testName);
			extentTest.fail(result.getThrowable());
			extentTest.addScreenCaptureFromPath(screenshotPath);
		} 
		else 
		{
			extentTest.log(Status.WARNING, "WebDriver instance not found. Screenshot not captured.");
			extentTest.fail(result.getThrowable());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		if (result.getThrowable() != null) 
		{
			extentTest.log(Status.INFO, result.getThrowable());
		}
		extentTest.log(Status.SKIP, testName + " was skipped");
	}

	@Override
	public void onFinish(ITestContext context)
	{
		extentReport.flush();

		String pathOfExtentReport= System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport= new File(pathOfExtentReport);
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}

package com.STL.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter 
{
	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReport=new ExtentReports();
		
		File extentReportFile= new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("STL Automation Test Result Report");
		sparkReporter.config().setDocumentTitle("STL QA REPORT");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp=new Properties();
		File configPropFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\STL\\Config\\Config.Properties");
		try															
		{
		FileInputStream fisConfigProp=new FileInputStream(configPropFile);
		configProp.load(fisConfigProp);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentReport.setSystemInfo("Brower Name", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Email",configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password",configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Version Of Java",System.getProperty("java.version"));
		
		return extentReport;
	}

}

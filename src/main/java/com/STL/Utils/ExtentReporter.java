package com.STL.Utils;

import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();

        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("STL Automation Execution Report");
        sparkReporter.config().setDocumentTitle("STL QA Execution Results");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        extentReport.attachReporter(sparkReporter);

        // Load config properties
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/STL/Config/Config.Properties");
            Properties prop = new Properties();
            prop.load(fis);

            extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
            extentReport.setSystemInfo("Browser", prop.getProperty("browser"));
            extentReport.setSystemInfo("User Email", prop.getProperty("validEmail"));
            extentReport.setSystemInfo("Password", prop.getProperty("validPassword"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // System-level info
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("User", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }
}


















































//package com.STL.Utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Properties;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class ExtentReporter 
//{
//	public static ExtentReports generateExtentReport()
//	{
//		ExtentReports extentReport=new ExtentReports();
//		
//		File extentReportFile= new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
//		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(extentReportFile);
//		
//		sparkReporter.config().setTheme(Theme.DARK);
//		sparkReporter.config().setReportName("STL Automation Test Result Report");
//		sparkReporter.config().setDocumentTitle("STL QA REPORT");
//		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
//		
//		extentReport.attachReporter(sparkReporter);
//		
//		Properties configProp=new Properties();
//		File configPropFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\STL\\Config\\Config.Properties");
//		try															
//		{
//		FileInputStream fisConfigProp=new FileInputStream(configPropFile);
//		configProp.load(fisConfigProp);
//		}
//		catch(Throwable e)
//		{
//			e.printStackTrace();
//		}
//		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
//		extentReport.setSystemInfo("Brower Name", configProp.getProperty("browser"));
//		extentReport.setSystemInfo("Email",configProp.getProperty("validEmail"));
//		extentReport.setSystemInfo("Password",configProp.getProperty("validPassword"));
//		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
//		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
//		extentReport.setSystemInfo("Version Of Java",System.getProperty("java.version"));
//		
//		return extentReport;
//	}
//
//}

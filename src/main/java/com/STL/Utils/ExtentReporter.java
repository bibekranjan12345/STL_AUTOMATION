package com.STL.Utils;

import java.io.InputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

    public static ExtentReports generateExtentReport(String suiteName) {
        ExtentReports extentReport = new ExtentReports();

        String sanitizedSuiteName = suiteName.replaceAll("\\s+", ""); // e.g., "SmokeSuite"
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new java.util.Date());
        String reportDir = System.getProperty("user.dir") + "/test-output/ExtentReports";
        new java.io.File(reportDir).mkdirs();
        
        String reportPath = reportDir + "/ExtentReport_" + sanitizedSuiteName + "_" + timestamp + ".html";
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("STL Automation Execution Report : " + suiteName);
        sparkReporter.config().setDocumentTitle("STL QA Execution Results");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        extentReport.attachReporter(sparkReporter);

        try {
            Properties prop = new Properties();
            InputStream input = ExtentReporter.class.getClassLoader().getResourceAsStream("config.properties");

            if (input != null) {
                prop.load(input);
                extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
                extentReport.setSystemInfo("Browser", prop.getProperty("browser"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        extentReport.setSystemInfo("Suite Name", suiteName);
        extentReport.setSystemInfo("OS", System.getProperty("os.name"));
        extentReport.setSystemInfo("User", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }
}

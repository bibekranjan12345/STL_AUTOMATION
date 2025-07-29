package com.STL.Utils;

import java.io.InputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();

        // Define report path
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        // Config report visuals
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("STL Automation Execution Report");
        sparkReporter.config().setDocumentTitle("STL QA Execution Results");
        sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        extentReport.attachReporter(sparkReporter);

        try {
            // Load properties from config.properties (located in src/test/resources)
            Properties prop = new Properties();
            InputStream input = ExtentReporter.class.getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath (src/test/resources)");
            }

            prop.load(input);

            // Add properties to the report
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

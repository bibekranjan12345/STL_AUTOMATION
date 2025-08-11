package com.STL.Utils;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
    private static ExtentReports extent;
    public static String generatedReportPath;

    public static void initReports(String suiteName) {
        if (extent != null) {
            return; // Already initialized
        }

        // Ensure report directory exists
        String reportDir = System.getProperty("user.dir") + "/test-output/ExtentReports";
        File folder = new File(reportDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Generate unique report name with timestamp
        String sanitizedSuiteName = suiteName.replaceAll("\\s+", "");
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        generatedReportPath = reportDir + "/ExtentReport_" + sanitizedSuiteName + "_" + timestamp + ".html";

        // Create Spark reporter
        ExtentSparkReporter spark = new ExtentSparkReporter(generatedReportPath);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setReportName("STL Automation Execution Report: " + suiteName);
        spark.config().setDocumentTitle("STL QA Execution Results");
        spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Load config.properties for system info
        try (InputStream input = ExtentReporter.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                Properties prop = new Properties();
                prop.load(input);
                extent.setSystemInfo("Application URL", prop.getProperty("url", "Not Set"));
                extent.setSystemInfo("Browser", prop.getProperty("browser", "Not Set"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        extent.setSystemInfo("Suite Name", suiteName);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}

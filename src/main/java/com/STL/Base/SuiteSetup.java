package com.STL.Base;

import java.io.File;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.STL.Utils.ExtentReporter;

public class SuiteSetup implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        // 1. Delete old reports
        String reportDirPath = System.getProperty("user.dir") + "/test-output/ExtentReports";
        File reportDir = new File(reportDirPath);
        if (reportDir.exists()) {
            deleteDirectory(reportDir);
        }
        reportDir.mkdirs();

        // 2. Initialize ExtentReports with suite name
        ExtentReporter.initReports(suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReporter.flushReports();
    }

    private void deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    f.delete();
                }
            }
        }
        dir.delete();
    }
}

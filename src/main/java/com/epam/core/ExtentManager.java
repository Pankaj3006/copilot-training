package com.epam.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ExtentManager {

    private static final String REPORT_DIRECTORY = "reports";
    private static final String REPORT_FILE = "extent-report.html";
    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            Path reportPath = Path.of(REPORT_DIRECTORY, REPORT_FILE);
            try {
                Files.createDirectories(reportPath.getParent());
            } catch (Exception exception) {
                throw new IllegalStateException("Unable to create report directory.", exception);
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());
            sparkReporter.config().setReportName("EPAM Automation Results");
            sparkReporter.config().setDocumentTitle("EPAM Extent Report");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Framework", "Selenium + TestNG + POM");
            extentReports.setSystemInfo("Application", "EPAM Automation Framework");
        }
        return extentReports;
    }

    public static synchronized void flush() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}

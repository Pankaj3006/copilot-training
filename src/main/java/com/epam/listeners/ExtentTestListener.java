package com.epam.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.epam.core.DriverFactory;
import com.epam.core.ExtentManager;
import com.epam.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReports extentReports = ExtentManager.getInstance();
        TEST.set(extentReports.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (TEST.get() != null) {
            TEST.get().log(Status.PASS, "Test passed.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (TEST.get() != null) {
            TEST.get().log(Status.FAIL, result.getThrowable());
        }
        if (DriverFactory.getDriver() != null) {
            String screenshotPath = ScreenshotUtil.capture(DriverFactory.getDriver(), result.getMethod().getMethodName());
            if (screenshotPath != null && TEST.get() != null) {
                TEST.get().addScreenCaptureFromPath(screenshotPath);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (TEST.get() != null) {
            TEST.get().log(Status.SKIP, "Test skipped.");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
        TEST.remove();
    }

    public static ExtentTest getTest() {
        return TEST.get();
    }
}

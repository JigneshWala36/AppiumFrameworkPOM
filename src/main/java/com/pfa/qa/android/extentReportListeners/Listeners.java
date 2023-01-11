package com.pfa.qa.android.extentReportListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.pfa.qa.android.util.AppiumUtils;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener {
    ExtentTest test;
    AppiumDriver driver;
    ExtentReports extentReports = ExtentReportsNG.getReporterObject();
    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());
//        ITestListener.super.onTestStart(result);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
//        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS, "Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        //screenshot code
        String screenshotPath = null;
        try {
            screenshotPath = getScreenshot(result.getMethod().getMethodName() + ".jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            test.fail(MediaEntityBuilder
                    .createScreenCaptureFromBase64String(AppiumUtils.convertImg_Base64(screenshotPath)).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.fail(result.getThrowable());
        test.log(Status.FAIL, "Test Failed");
/*
        Broken Image getting generated on  Extent Report for the below execution of code. Rahul Shetty
//        try {
//            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver")
//                    .get(result.getInstance());
//        } catch (Exception e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//        try {
//            test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(),driver), result.getMethod().getMethodName());
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
*/
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extentReports.flush();
    }
}

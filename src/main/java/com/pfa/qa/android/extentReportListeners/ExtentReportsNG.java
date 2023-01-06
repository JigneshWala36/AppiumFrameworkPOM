package com.pfa.qa.android.extentReportListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pfa.qa.android.base.TestBase;

public class ExtentReportsNG extends TestBase {


    public static ExtentReports getReporterObject() {

        String path = System.getProperty("user.dir", "\\reports\\index.html");
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Appium Framework Extent Demo");
        reporter.config().setDocumentTitle("Test Result");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "Jignesh Wala");
        return extentReports;


    }
}

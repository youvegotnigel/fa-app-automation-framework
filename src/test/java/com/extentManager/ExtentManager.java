/**
 * @author  : youvegotnigel
 * created on 2021/01/30
 */
package com.extentManager;

import base.BaseTests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    private static String fileSeparator = File.separator;

    public static void setExtent() {

        // specify location of the report
        htmlReporter = new ExtentHtmlReporter(BaseTests.getExtentReportDirectory() + fileSeparator + "Execution Results - " + BaseTests.getTimestamp() + ".html");

        //configure extent report from xml file
        //htmlReporter.loadXMLConfig(BaseTests.getProjectDirectory() + "/html-config.xml");

        //configure extent report from code
        htmlReporter.config().setDocumentTitle("FA APP Automation Report"); // Tile of report
        htmlReporter.config().setReportName("Functional Testing"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Passing General information
        extent.setSystemInfo("Application Name", "FA APP");
        extent.setSystemInfo("Build No", "101");
        extent.setSystemInfo("Environment", "nQA");
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("Test Developer", "nigel");

    }


    public static void endReport() {
        extent.flush();
    }


}

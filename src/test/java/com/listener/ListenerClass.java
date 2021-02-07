/**
 * @author : youvegotnigel
 * created on 2021/01/30
 */
package com.listener;

import base.BaseTests;
import com.aventstack.extentreports.Status;
import com.extentManager.ExtentManager;
import com.utility.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ListenerClass extends ExtentManager implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        test = extent.createTest(result.getName());
        Log.info("The test case " + result.getName() + " has been started");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "TEST CASE PASSED IS " + result.getName());
            Log.info("The test case " + result.getName() + " has been passed");
        }

    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report

            Log.warn("The test case " + result.getName() + " has been failed");
            Log.error("The error is : \n" + result.getThrowable());
            String screenshotPath = null;
            try {
                screenshotPath = BaseTests.getScreenshot(BaseTests.driver, result.getName());
                Log.info("Screenshot taken for failed test : " + result.getName());
            } catch (IOException e) {
                e.printStackTrace();
                Log.error("Not able to take Screenshot of failed test : " + result.getName());
                Log.error("Error below : ");
                Log.error("BOOM!" + e);
            }
            try {
                test.addScreenCaptureFromPath(screenshotPath);// adding screenshot
                Log.info("Screenshot attached to report of failed test : " + result.getName());
            } catch (IOException e) {
                e.printStackTrace();
                Log.error("Not able attach Screenshot of failed test : " + result.getName());
                Log.error("Error below : ");
                Log.error("BOOM!" + e);
            }
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName());
            System.out.println("Test Skipped : " + result.getName());
            Log.info("The test case " + result.getName() + " has been skipped");
        }

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
    }
}

package com.sauceyyyy.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Allure;
import com.sauceyyyy.utils.LoggerUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.ByteArrayInputStream;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        LoggerUtils.info("TEST STARTED: " + result.getMethod().getMethodName());
        Allure.feature(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtils.testPassed("Test passed: " + result.getMethod().getMethodName());
        LoggerUtils.info("ok Test Duration: " + (result.getEndMillis() - result.getStartMillis()) + "ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtils.testFailed("Test failed: " + result.getMethod().getMethodName());
        LoggerUtils.error("Failure Reason: " + result.getThrowable().getMessage());
        LoggerUtils.info("x Test Duration: " + (result.getEndMillis() - result.getStartMillis()) + "ms");


        try {
            Object testObject = result.getInstance();
            WebDriver driver = null;

            if (testObject != null) {
                java.lang.reflect.Field[] fields = testObject.getClass().getDeclaredFields();
                for (java.lang.reflect.Field field : fields) {
                    if (WebDriver.class.isAssignableFrom(field.getType())) {
                        field.setAccessible(true);
                        driver = (WebDriver) field.get(testObject);
                        break;
                    }
                }
            }

            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
                LoggerUtils.info("Screenshot attached to Allure report");
            }
        } catch (Exception e) {
            LoggerUtils.warn("Could not take screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtils.warn("Test skipped: " + result.getMethod().getMethodName());
        LoggerUtils.warn("Reason: " + result.getSkipCausedBy());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LoggerUtils.warn("Test failed but within success percentage: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(org.testng.ITestContext context) {

        LoggerUtils.info("TEST SUITE STARTED: " + context.getName());
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {

        LoggerUtils.info("TEST SUITE FINISHED: " + context.getName());
        LoggerUtils.info("Total Tests: " + context.getAllTestMethods().length);
        LoggerUtils.info("Passed: " + context.getPassedTests().size());
        LoggerUtils.info("Failed: " + context.getFailedTests().size());
        LoggerUtils.info("Skipped: " + context.getSkippedTests().size());

    }
}

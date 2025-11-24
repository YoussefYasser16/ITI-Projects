package com.sauceyyyy.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestData.EXPLICIT_WAIT_TIMEOUT));
    }


    public WebDriverWait getWait() {
        return this.wait;
    }


    public WebElement waitForElementToBeVisible(By locator) {
        LoggerUtils.info("Waiting for element to be visible: " + locator);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtils.error("Element not visible: " + locator);
            throw e;
        }
    }

    public boolean isElementVisible(By locator) {
        LoggerUtils.debug("Checking if element is visible: " + locator);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.debug("Element not visible or not found: " + locator);
            return false;
        }
    }

    public List<WebElement> waitForAllElementsToBeVisible(By locator) {
        LoggerUtils.info("Waiting for all elements to be visible: " + locator);
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            LoggerUtils.error("Elements not visible: " + locator);
            throw e;
        }
    }

    public WebElement waitForElementToBeClickable(By locator) {
        LoggerUtils.info("Waiting for element to be clickable: " + locator);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            LoggerUtils.error("Element not clickable: " + locator);
            throw e;
        }
    }

    public WebElement waitForElementToBePresent(By locator) {
        LoggerUtils.info("Waiting for element to be present: " + locator);
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtils.error("Element not present: " + locator);
            throw e;
        }
    }


    public WebElement fluentWaitForElement(By locator) {
        LoggerUtils.info("Fluent wait for element: " + locator);
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TestData.FLUENT_WAIT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(TestData.POLLING_INTERVAL))
                .ignoring(Exception.class);
        try {
            return fluentWait.until(d -> d.findElement(locator));
        } catch (Exception e) {
            LoggerUtils.error("Fluent wait failed for: " + locator);
            throw e;
        }
    }


    public boolean waitForUrlToContain(String url) {
        LoggerUtils.info("Waiting for URL to contain: " + url);
        try {
            return wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            LoggerUtils.error("URL does not contain: " + url);
            return false;
        }
    }

    public boolean waitForElementToDisappear(By locator) {
        LoggerUtils.info("Waiting for element to disappear: " + locator);
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            LoggerUtils.error("Element did not disappear: " + locator);
            return false;
        }
    }

    public boolean waitForTextToBePresentInElement(By locator, String text) {
        LoggerUtils.info("Waiting for text '" + text + "' in element: " + locator);
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            LoggerUtils.error("Text not found in element: " + text);
            return false;
        }
    }
}

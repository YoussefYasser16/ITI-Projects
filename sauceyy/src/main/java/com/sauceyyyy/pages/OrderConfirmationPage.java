package com.sauceyyyy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import com.sauceyyyy.utils.WaitUtils;

public class OrderConfirmationPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private final By confirmationMessage = By.cssSelector("[data-test='complete-header']");
    private final By orderDetails = By.cssSelector("[data-test='complete-text']");
    private final By checkIcon = By.className("pony_express");
    private final By backHomeButton = By.id("back-to-products");

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    public void navigateToOrderConfirmation() {
        LoggerUtils.info("Navigating to order confirmation: " + TestData.CHECKOUT_COMPLETE_URL);
        driver.navigate().to(TestData.CHECKOUT_COMPLETE_URL);
    }

    public void clickBackHomeButton() {
        LoggerUtils.info("Clicking Back Home button");
        waitUtils.waitForElementToBeClickable(backHomeButton).click();
    }


    public String getConfirmationMessage() {
        LoggerUtils.info("Getting confirmation message");
        try {
            return waitUtils.waitForElementToBeVisible(confirmationMessage).getText();
        } catch (Exception e) {
            LoggerUtils.error("Confirmation message not found: " + e.getMessage());
            return "";
        }
    }

    public String getOrderDetails() {
        LoggerUtils.info("Getting order details");
        try {
            return waitUtils.waitForElementToBeVisible(orderDetails).getText();
        } catch (Exception e) {
            LoggerUtils.error("Order details not found: " + e.getMessage());
            return "";
        }
    }

    public boolean isOrderConfirmationPageDisplayed() {
        LoggerUtils.info("Verifying order confirmation page is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(confirmationMessage).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Order confirmation page not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isConfirmationMessageDisplayed() {
        LoggerUtils.info("Verifying confirmation message is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(confirmationMessage).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Confirmation message not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isCheckIconDisplayed() {
        LoggerUtils.info("Verifying check icon is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(checkIcon).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Check icon not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isBackHomeButtonDisplayed() {
        LoggerUtils.info("Verifying Back Home button is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(backHomeButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Back Home button not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isOrderSuccessful() {
        LoggerUtils.info("Checking if order is successful");
        try {
            String message = getConfirmationMessage();
            return message.equalsIgnoreCase(TestData.ORDER_SUCCESS_MESSAGE);
        } catch (Exception e) {
            LoggerUtils.error("Could not verify order success: " + e.getMessage());
            return false;
        }
    }
}

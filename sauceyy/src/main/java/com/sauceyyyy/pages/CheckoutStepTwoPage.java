package com.sauceyyyy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import com.sauceyyyy.utils.WaitUtils;

public class CheckoutStepTwoPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private final By cartItems = By.cssSelector("[data-test='inventory-item']");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By itemPrices = By.cssSelector("[data-test='inventory-item-price']");
    private final By paymentInfo = By.cssSelector("[data-test='payment-info-value']");
    private final By shippingInfo = By.cssSelector("[data-test='shipping-info-value']");
    private final By totalValue = By.cssSelector("[data-test='total-label']");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");
    private final By checkoutOverviewTitle = By.cssSelector("[data-test='title']");

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    public void navigateToCheckoutStepTwo() {
        LoggerUtils.info("Navigating to checkout step two: " + TestData.CHECKOUT_STEP_TWO_URL);
        driver.navigate().to(TestData.CHECKOUT_STEP_TWO_URL);
    }

    public void clickCancelButton() {
        LoggerUtils.info("Clicking Cancel button");
        waitUtils.waitForElementToBeClickable(cancelButton).click();
    }

    public void clickFinishButton() {
        LoggerUtils.info("Clicking Finish button");
        waitUtils.waitForElementToBeClickable(finishButton).click();
    }


    public int getOrderItemCount() {
        LoggerUtils.info("Getting order item count");
        try {
            return waitUtils.waitForAllElementsToBeVisible(cartItems).size();
        } catch (Exception e) {
            LoggerUtils.error("Failed to get order item count: " + e.getMessage());
            return 0;
        }
    }

    public List<String> getOrderItemNames() {
        LoggerUtils.info("Getting all order item names");
        try {
            return waitUtils.waitForAllElementsToBeVisible(itemNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        } catch (Exception e) {
            LoggerUtils.error("Failed to get order item names: " + e.getMessage());
            return List.of();
        }
    }

    public List<String> getOrderItemPrices() {
        LoggerUtils.info("Getting all order item prices");
        try {
            return waitUtils.waitForAllElementsToBeVisible(itemPrices)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        } catch (Exception e) {
            LoggerUtils.error("Failed to get order item prices: " + e.getMessage());
            return List.of();
        }
    }

    public String getPaymentInformation() {
        LoggerUtils.info("Getting payment information");
        try {
            return waitUtils.waitForElementToBeVisible(paymentInfo).getText();
        } catch (Exception e) {
            LoggerUtils.error("Payment information not found: " + e.getMessage());
            return "";
        }
    }

    public String getShippingInformation() {
        LoggerUtils.info("Getting shipping information");
        try {
            return waitUtils.waitForElementToBeVisible(shippingInfo).getText();
        } catch (Exception e) {
            LoggerUtils.error("Shipping information not found: " + e.getMessage());
            return "";
        }
    }

    public String getTotalPrice() {
        LoggerUtils.info("Getting total price");
        try {
            return waitUtils.waitForElementToBeVisible(totalValue).getText();
        } catch (Exception e) {
            LoggerUtils.error("Total price not found: " + e.getMessage());
            return "";
        }
    }

    public boolean isOrderItemDisplayed(String itemName) {
        LoggerUtils.info("Checking if order item is displayed: " + itemName);
        return getOrderItemNames().stream().anyMatch(name -> name.equalsIgnoreCase(itemName));
    }

    public boolean isCheckoutStepTwoPageDisplayed() {
        LoggerUtils.info("Verifying checkout step two page is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(checkoutOverviewTitle).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Checkout step two page not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isFinishButtonDisplayed() {
        LoggerUtils.info("Verifying finish button is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(finishButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Finish button not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isCancelButtonDisplayed() {
        LoggerUtils.info("Verifying cancel button is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(cancelButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Cancel button not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isPaymentInfoDisplayed() {
        LoggerUtils.info("Verifying payment information is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(paymentInfo).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Payment information not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isShippingInfoDisplayed() {
        LoggerUtils.info("Verifying shipping information is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(shippingInfo).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Shipping information not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isTotalPriceDisplayed() {
        LoggerUtils.info("Verifying total price is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(totalValue).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Total price not displayed: " + e.getMessage());
            return false;
        }
    }
}

package com.sauceyyyy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import com.sauceyyyy.utils.WaitUtils;

public class CartPage {

    private WebDriver driver;
    private WaitUtils waitUtils;


    private final By cartItems = By.cssSelector("[data-test='inventory-item']");
    private final By itemNames = By.cssSelector("[data-test='inventory-item-name']");
    private final By itemPrices = By.cssSelector("[data-test='inventory-item-price']");
    private final By itemQuantity = By.cssSelector("[data-test='item-quantity']");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By checkoutButton = By.id("checkout");
    private final By cartHeader = By.cssSelector("[data-test='title']");


    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    public void navigateToCart() {
        LoggerUtils.info("Navigating to cart page: " + TestData.CART_URL);
        driver.navigate().to(TestData.CART_URL);
    }

    public void clickRemoveByProductName(String productName) {
        LoggerUtils.info("Removing product from cart: " + productName);
        String dataTestId = "remove-" + productName.toLowerCase()
                .replace("test.allthethings() ", "test.allthethings()-")
                .replace(" ", "-");
        By removeButton = By.cssSelector("[data-test='" + dataTestId + "']");
        waitUtils.waitForElementToBeClickable(removeButton).click();
    }

    public void clickContinueShopping() {
        LoggerUtils.info("Clicking Continue Shopping button");
        waitUtils.waitForElementToBeClickable(continueShoppingButton).click();
    }

    public void clickCheckout() {
        LoggerUtils.info("Clicking Checkout button");
        waitUtils.waitForElementToBeClickable(checkoutButton).click();
    }


    public int getCartItemCount() {
        LoggerUtils.info("Getting cart item count");
        try {
            return waitUtils.waitForAllElementsToBeVisible(cartItems).size();
        } catch (Exception e) {
            LoggerUtils.error("Failed to get cart item count: " + e.getMessage());
            return 0;
        }
    }

    public List<String> getCartItemNames() {
        LoggerUtils.info("Getting all cart item names");
        List<WebElement> nameElements = waitUtils.waitForAllElementsToBeVisible(itemNames);
        List<String> names = new ArrayList<>();
        for (WebElement nameElement : nameElements) {
            names.add(nameElement.getText());
        }
        return names;
    }

    public List<String> getCartItemPrices() {
        LoggerUtils.info("Getting all cart item prices");
        List<WebElement> priceElements = waitUtils.waitForAllElementsToBeVisible(itemPrices);
        List<String> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            prices.add(priceElement.getText());
        }
        return prices;
    }

    public String getItemQuantity(String productName) {
        LoggerUtils.info("Getting quantity for product: " + productName);
        List<WebElement> items = waitUtils.waitForAllElementsToBeVisible(cartItems);
        for (WebElement item : items) {
            if (item.findElement(itemNames).getText().equalsIgnoreCase(productName)) {
                return item.findElement(itemQuantity).getText();
            }
        }
        LoggerUtils.error("Quantity not found for product: " + productName);
        return "";
    }

    public boolean isProductInCart(String productName) {
        LoggerUtils.info("Checking if product is in cart: " + productName);
        List<String> cartItemNames = getCartItemNames();
        for (String name : cartItemNames) {
            if (name.equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCartEmpty() {
        LoggerUtils.info("Checking if cart is empty");
        return getCartItemCount() == 0;
    }

    public boolean isCartPageDisplayed() {
        LoggerUtils.info("Verifying cart page is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(cartHeader).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Cart page not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isContinueShoppingButtonDisplayed() {
        LoggerUtils.info("Verifying Continue Shopping button is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(continueShoppingButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Continue Shopping button not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isCheckoutButtonDisplayed() {
        LoggerUtils.info("Verifying Checkout button is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(checkoutButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Checkout button not displayed: " + e.getMessage());
            return false;
        }
    }
}

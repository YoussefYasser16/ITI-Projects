package com.sauceyyyy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.sauceyyyy.utils.LoggerUtils;
import com.sauceyyyy.utils.WaitUtils;

public class ProductDetailPage {

    private WebDriver driver;
    private WaitUtils waitUtils;


    private final By productName = By.cssSelector("[data-test='inventory-item-name']");
    private final By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private final By productDescription = By.cssSelector("[data-test='inventory-item-desc']");
    private final By productImage = By.className("inventory_details_img");
    private final By addToCartButton = By.xpath("//button[contains(@id, 'add-to-cart')]");
    private final By removeButton = By.xpath("//button[contains(@id, 'remove')]");
    private final By backToProductsLink = By.id("back-to-products");


    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    public void clickAddToCart() {
        LoggerUtils.info("Clicking Add to Cart on product detail page");
        waitUtils.waitForElementToBeClickable(addToCartButton).click();
    }

    public void clickRemove() {
        LoggerUtils.info("Clicking Remove on product detail page");
        waitUtils.waitForElementToBeClickable(removeButton).click();
    }

    public void clickBackToProducts() {
        LoggerUtils.info("Clicking Back to Products link");
        waitUtils.waitForElementToBeClickable(backToProductsLink).click();
    }


    public String getProductName() {
        LoggerUtils.info("Getting product name from detail page");
        try {
            return waitUtils.waitForElementToBeVisible(productName).getText();
        } catch (Exception e) {
            LoggerUtils.error("Could not get product name: " + e.getMessage());
            return "";
        }
    }

    public String getProductPrice() {
        LoggerUtils.info("Getting product price from detail page");
        try {
            return waitUtils.waitForElementToBeVisible(productPrice).getText();
        } catch (Exception e) {
            LoggerUtils.error("Could not get product price: " + e.getMessage());
            return "";
        }
    }

    public String getProductDescription() {
        LoggerUtils.info("Getting product description from detail page");
        try {
            return waitUtils.waitForElementToBeVisible(productDescription).getText();
        } catch (Exception e) {
            LoggerUtils.error("Could not get product description: " + e.getMessage());
            return "";
        }
    }

    public boolean isProductImageDisplayed() {
        LoggerUtils.info("Verifying product image is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(productImage).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Product image not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isAddToCartButtonDisplayed() {
        LoggerUtils.info("Verifying Add to Cart button is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(addToCartButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Add to Cart button not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isRemoveButtonDisplayed() {
        LoggerUtils.info("Verifying Remove button is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(removeButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Remove button not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isProductDetailPageDisplayed() {
        LoggerUtils.info("Verifying product detail page is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(productName).isDisplayed() &&
                    waitUtils.waitForElementToBeVisible(productDescription).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Product detail page not displayed: " + e.getMessage());
            return false;
        }
    }

    public String getAddToCartButtonText() {
        LoggerUtils.info("Getting Add to Cart button text");
        try {
            return waitUtils.waitForElementToBeVisible(addToCartButton).getText();
        } catch (Exception e) {
            try {
                return waitUtils.waitForElementToBeVisible(removeButton).getText();
            } catch (Exception ex) {
                LoggerUtils.error("Could not get button text: " + ex.getMessage());
                return "";
            }
        }
    }
}

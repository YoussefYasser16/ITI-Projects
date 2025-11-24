package com.sauceyyyy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import com.sauceyyyy.utils.WaitUtils;

public class ProductsPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private final By cartIcon = By.id("shopping_cart_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By sortDropdown = By.className("product_sort_container");
    private final By mainInventoryContainer = By.id("inventory_container");
    private final By productItems = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By productDescriptions = By.cssSelector(".inventory_item_desc");



    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    public void navigateToProductsPage() {
        LoggerUtils.info("Navigating to products page: " + TestData.INVENTORY_URL);
        driver.navigate().to(TestData.INVENTORY_URL);
    }

    public void clickAddToCartByProductName(String productName) {
        LoggerUtils.info("Clicking Add to Cart for product: " + productName);
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.id(buttonId);
        try {
            waitUtils.waitForElementToBeClickable(addToCartButton).click();
            LoggerUtils.info("Successfully added " + productName + " to cart");
        } catch (Exception e) {
            LoggerUtils.error("Could not add product to cart: " + productName);
            throw e;
        }
    }

    public void clickRemoveByProductName(String productName) {
        LoggerUtils.info("Clicking Remove for product: " + productName);
        String buttonId = "remove-" + productName.toLowerCase().replace(" ", "-");
        By removeButton = By.id(buttonId);
        try {
            waitUtils.waitForElementToBeClickable(removeButton).click();
            LoggerUtils.info("Successfully removed " + productName);
        } catch (Exception e) {
            LoggerUtils.error("Could not remove product: " + productName);
            throw e;
        }
    }

    public String getCartBadgeCount() {
        LoggerUtils.info("Getting cart badge count");
        try {
            return waitUtils.waitForElementToBeVisible(cartBadge).getText();
        } catch (Exception e) {
            LoggerUtils.warn("Cart badge not found, assuming 0 items");
            return "0";
        }
    }

    public void clickCartIcon() {
        LoggerUtils.info("Clicking cart icon");
        waitUtils.waitForElementToBeClickable(cartIcon).click();
    }

    public void selectSortOption(String option) {
        LoggerUtils.info("Selecting sort option: " + option);
        waitUtils.waitForElementToBeClickable(sortDropdown).click();
        By optionLocator = By.xpath("//option[contains(text(), '" + option + "')]");
        waitUtils.waitForElementToBeClickable(optionLocator).click();
    }

    public void clickProductByName(String productName) {
        LoggerUtils.info("Clicking on product: " + productName);
        List<WebElement> productElements = getAllProductNames();
        for (WebElement productElement : productElements) {
            if (productElement.getText().equalsIgnoreCase(productName)) {
                productElement.click();
                return;
            }
        }
        LoggerUtils.error("Could not find product to click: " + productName);
        throw new RuntimeException("Product not found: " + productName);
    }


    public int getProductCount() {
        LoggerUtils.info("Getting product count");

        return waitUtils.waitForAllElementsToBeVisible(productItems).size();
    }

    public List<WebElement> getAllProductNames() {
        LoggerUtils.info("Getting all product names");

        waitUtils.waitForElementToBeVisible(mainInventoryContainer);
        return waitUtils.waitForAllElementsToBeVisible(productNames);
    }

    public List<String> getAllProductPrices() {
        LoggerUtils.info("Getting all product prices");
        List<WebElement> priceElements = waitUtils.waitForAllElementsToBeVisible(productPrices);
        List<String> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            prices.add(priceElement.getText());
        }
        return prices;
    }

    public String getProductNameFromListing(String productName) {
        List<WebElement> allNames = getAllProductNames();
        return allNames.stream()
                .filter(e -> e.getText().equalsIgnoreCase(productName))
                .map(WebElement::getText)
                .findFirst()
                .orElse("");
    }

    public String getProductPriceFromListing(String productName) {
        List<WebElement> allNames = getAllProductNames();
        List<String> allPrices = getAllProductPrices();
        for (int i = 0; i < allNames.size(); i++) {
            if (allNames.get(i).getText().equalsIgnoreCase(productName)) {
                return allPrices.get(i);
            }
        }
        return "";
    }

    public String getProductDescriptionFromListing(String productName) {
        List<WebElement> allNames = getAllProductNames();
        List<WebElement> allDescriptions = waitUtils.waitForAllElementsToBeVisible(productDescriptions);

        for (int i = 0; i < allNames.size(); i++) {
            if (allNames.get(i).getText().equalsIgnoreCase(productName)) {
                return allDescriptions.get(i).getText();
            }
        }
        return "";
    }

    public boolean isProductDisplayed(String productName) {
        LoggerUtils.info("Checking if product is displayed: " + productName);
        List<WebElement> productElements = getAllProductNames();
        for (WebElement productElement : productElements) {
            if (productElement.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isProductsPageDisplayed() {
        LoggerUtils.info("Verifying products page is displayed");
        try {

            return waitUtils.waitForElementToBeVisible(mainInventoryContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddToCartButtonDisplayed(String productName) {
        LoggerUtils.info("Checking if Add to Cart button is displayed for: " + productName);
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.id(buttonId);
        return waitUtils.isElementVisible(addToCartButton);
    }

    public String getSortDropdownValue() {
        LoggerUtils.info("Getting sort dropdown current value");
        try {
            return waitUtils.waitForElementToBeVisible(sortDropdown).getAttribute("value");
        } catch (Exception e) {
            LoggerUtils.warn("Could not get sort dropdown value: " + e.getMessage());
            return "";
        }
    }
}

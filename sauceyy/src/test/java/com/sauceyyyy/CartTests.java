package com.sauceyyyy;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import java.util.List;
@Listeners({com.sauceyyyy.listeners.TestListener.class, AllureTestNg.class})
public class CartTests extends BaseTest {

    @BeforeMethod
    public void cartTestSetup() {
        loginPage.navigateToLoginPage();
        loginPage.login(TestData.STANDARD_USER, TestData.DEFAULT_PASSWORD);
        productsPage.navigateToProductsPage();
        menuPage.clickResetAppState();
        productsPage.navigateToProductsPage(); // Added to ensure fresh state after reset
    }

    @Test(description = "TC-021: Verify Add to Cart button changes to Remove after clicking")
    @Description("Test that Add to Cart button changes to Remove after item is added")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddToCartButtonChangesToRemove() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-021: Testing Add to Cart button changes to Remove");

        try {
            softAssert.assertTrue(productsPage.isAddToCartButtonDisplayed(TestData.BACKPACK_PRODUCT),
                    "Add to Cart button should be displayed");

            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);

            softAssert.assertFalse(productsPage.isAddToCartButtonDisplayed(TestData.BACKPACK_PRODUCT),
                    "Add to Cart button should change to Remove");

            LoggerUtils.testPassed("TC-021: Add to Cart button changed to Remove");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-021 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-022: Verify cart badge increments when adding items")
    @Description("Test that cart badge shows correct count when items are added")
    @Severity(SeverityLevel.CRITICAL)
    public void testCartBadgeIncrements() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-022: Testing cart badge increments");

        try {

            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
            String badgeCount = productsPage.getCartBadgeCount();
            softAssert.assertEquals(badgeCount, "1", "Cart badge should show 1");


            productsPage.clickAddToCartByProductName(TestData.BIKE_LIGHT_PRODUCT);
            String badgeCount2 = productsPage.getCartBadgeCount();
            softAssert.assertEquals(badgeCount2, "2", "Cart badge should show 2");

            LoggerUtils.testPassed("TC-022: Cart badge incremented correctly");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-022 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-023: Verify cart badge is visible in header")
    @Severity(SeverityLevel.NORMAL)
    public void testCartBadgeVisibleInHeader() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-023: Testing cart badge is visible in header");

        try {
            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);

            String badgeCount = productsPage.getCartBadgeCount();
            softAssert.assertFalse(badgeCount.isEmpty(), "Cart badge should be visible and contain count");
            softAssert.assertEquals(badgeCount, "1", "Badge count should match items added");

            LoggerUtils.testPassed("TC-023: Cart badge visible in header");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-023 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-024: Verify clicking cart icon navigates to cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testClickCartIconNavigatesToCartPage() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-024: Testing clicking cart icon navigates to cart page");

        try {
            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
            productsPage.clickCartIcon();

            softAssert.assertTrue(cartPage.isCartPageDisplayed(),
                    "Cart page should be displayed");
            softAssert.assertTrue(driver.getCurrentUrl().contains(TestData.CART_URL),
                    "URL should be cart page");

            LoggerUtils.testPassed("TC-024: Clicking cart icon navigates to cart page");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-024 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-025: Verify Remove button works on products page")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveButtonWorksOnProductsPage() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-025: Testing Remove button on products page");

        try {
            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);

            String badgeCountBefore = productsPage.getCartBadgeCount();
            softAssert.assertEquals(badgeCountBefore, "1", "Badge should show 1 item");

            productsPage.clickRemoveByProductName(TestData.BACKPACK_PRODUCT);

            String badgeCountAfter = productsPage.getCartBadgeCount();
            softAssert.assertEquals(badgeCountAfter, "0", "Badge should show 0 items after remove");

            LoggerUtils.testPassed("TC-025: Remove button works on products page");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-025 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-026: Verify cart page displays correct items")
    @Severity(SeverityLevel.NORMAL)
    public void testCartPageDisplaysCorrectItems() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-026: Testing cart page displays correct items");

        try {
            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
            productsPage.clickAddToCartByProductName(TestData.BIKE_LIGHT_PRODUCT);
            productsPage.clickCartIcon();

            List<String> cartItems = cartPage.getCartItemNames();
            softAssert.assertTrue(cartItems.contains(TestData.BACKPACK_PRODUCT),
                    "Cart should contain backpack");
            softAssert.assertTrue(cartItems.contains(TestData.BIKE_LIGHT_PRODUCT),
                    "Cart should contain bike light");

            LoggerUtils.testPassed("TC-026: Cart page displays correct items");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-026 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-027: Verify cart page shows item quantity")
    @Severity(SeverityLevel.NORMAL)
    public void testCartPageShowsItemQuantity() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-027: Testing cart page shows item quantity");

        try {
            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
            productsPage.clickCartIcon();

            String backpackQty = cartPage.getItemQuantity(TestData.BACKPACK_PRODUCT);
            softAssert.assertEquals(backpackQty, "1", "Backpack quantity should be 1");

            LoggerUtils.testPassed("TC-027: Cart page shows item quantity");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-027 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-028: Verify Remove button works on cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveButtonWorksOnCartPage() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-028: Testing Remove button on cart page");

        try {
            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
            productsPage.clickAddToCartByProductName(TestData.BIKE_LIGHT_PRODUCT);
            productsPage.clickCartIcon();

            int itemCountBefore = cartPage.getCartItemCount();
            softAssert.assertEquals(itemCountBefore, 2, "Cart should have 2 items");

            cartPage.clickRemoveByProductName(TestData.BACKPACK_PRODUCT);

            int itemCountAfter = cartPage.getCartItemCount();
            softAssert.assertEquals(itemCountAfter, 1, "Cart should have 1 item after remove");

            LoggerUtils.testPassed("TC-028: Remove button works on cart page");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-028 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-029: Verify Continue Shopping button on cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testContinueShoppingButton() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-029: Testing Continue Shopping button");

        try {
            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
            productsPage.clickCartIcon();

            softAssert.assertTrue(cartPage.isContinueShoppingButtonDisplayed(),
                    "Continue Shopping button should be displayed");

            cartPage.clickContinueShopping();

            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Should return to products page");

            LoggerUtils.testPassed("TC-029: Continue Shopping button works");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-029 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-030: Verify Checkout button is visible on cart page")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckoutButtonVisible() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-030: Testing Checkout button visibility");

        try {
            productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
            productsPage.clickCartIcon();

            softAssert.assertTrue(cartPage.isCheckoutButtonDisplayed(),
                    "Checkout button should be visible");

            LoggerUtils.testPassed("TC-030: Checkout button is visible");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-030 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }
}

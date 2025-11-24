package com.sauceyyyy;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeMethod; // Import BeforeMethod
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
@Listeners({com.sauceyyyy.listeners.TestListener.class, AllureTestNg.class})
public class OrderConfirmationTests extends BaseTest {

    @BeforeMethod
    public void orderConfirmationSetup() {
        loginPage.navigateToLoginPage();
        loginPage.login(TestData.STANDARD_USER, TestData.DEFAULT_PASSWORD);
        menuPage.clickResetAppState();
        productsPage.navigateToProductsPage();
        productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
        productsPage.clickAddToCartByProductName(TestData.BIKE_LIGHT_PRODUCT);
        productsPage.clickCartIcon();
        cartPage.clickCheckout();
        checkoutStepOnePage.fillCheckoutInformationWithDefaults();
        checkoutStepOnePage.clickContinueButton();
        checkoutStepTwoPage.clickFinishButton();
    }

    @Test(description = "TC-050: Verify order confirmation message")
    @Description("Test that order confirmation message is displayed after successful order")
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderConfirmationMessage() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-050: Testing order confirmation message");

        try {
            softAssert.assertTrue(orderConfirmationPage.isOrderConfirmationPageDisplayed(),
                    "Order confirmation page should be displayed");

            String confirmationMessage = orderConfirmationPage.getConfirmationMessage();
            softAssert.assertTrue(confirmationMessage.contains(TestData.ORDER_SUCCESS_MESSAGE),
                    "Confirmation message should contain success message");

            LoggerUtils.testPassed("TC-050: Order confirmation message verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-050 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-051: Verify check icon is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckIconDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-051: Testing check icon is displayed");

        try {
            softAssert.assertTrue(orderConfirmationPage.isCheckIconDisplayed(),
                    "Check icon should be displayed");

            LoggerUtils.testPassed("TC-051: Check icon is displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-051 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-052: Verify Back Home button is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testBackHomeButtonDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-052: Testing Back Home button is displayed");

        try {
            softAssert.assertTrue(orderConfirmationPage.isBackHomeButtonDisplayed(),
                    "Back Home button should be displayed");

            LoggerUtils.testPassed("TC-052: Back Home button is displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-052 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-053: Verify Back Home button navigates to products")
    @Severity(SeverityLevel.NORMAL)
    public void testBackHomeButtonNavigation() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-053: Testing Back Home button navigation");

        try {
            orderConfirmationPage.clickBackHomeButton();

            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Should navigate to products page");
            softAssert.assertTrue(driver.getCurrentUrl().contains(TestData.INVENTORY_URL),
                    "URL should be products page");

            LoggerUtils.testPassed("TC-053: Back Home button navigates to products");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-053 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-054: Verify order is successful")
    @Severity(SeverityLevel.CRITICAL)
    public void testOrderIsSuccessful() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-054: Testing order is successful");

        try {
            boolean isSuccessful = orderConfirmationPage.isOrderSuccessful();
            softAssert.assertTrue(isSuccessful, "Order should be successful");

            LoggerUtils.testPassed("TC-054: Order is successful");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-054 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-055: Verify order details text is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testOrderDetailsTextDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-055: Testing order details text is displayed");

        try {
            String orderDetails = orderConfirmationPage.getOrderDetails();
            softAssert.assertFalse(orderDetails.isEmpty(),
                    "Order details text should be displayed");

            LoggerUtils.testPassed("TC-055: Order details text is displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-055 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }
}

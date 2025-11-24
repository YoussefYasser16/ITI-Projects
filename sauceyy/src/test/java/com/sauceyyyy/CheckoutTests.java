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
public class CheckoutTests extends BaseTest {

    @BeforeMethod
    public void checkoutTestSetup() {
        loginPage.navigateToLoginPage();
        loginPage.login(TestData.STANDARD_USER, TestData.DEFAULT_PASSWORD);
        menuPage.clickResetAppState();
        productsPage.navigateToProductsPage();
        productsPage.clickAddToCartByProductName(TestData.BACKPACK_PRODUCT);
        productsPage.clickAddToCartByProductName(TestData.BIKE_LIGHT_PRODUCT);
        productsPage.clickCartIcon();
        cartPage.clickCheckout();
    }

    @Test(description = "TC-038: Verify checkout form displays required fields - Step 1")
    @Description("Test that all required form fields are visible on checkout step 1")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutStep1FieldsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-038: Testing checkout step 1 fields are displayed");

        try {
            softAssert.assertTrue(checkoutStepOnePage.isCheckoutStepOnePageDisplayed(),
                    "Checkout step 1 page should be displayed");
            softAssert.assertTrue(checkoutStepOnePage.isFirstNameFieldDisplayed(),
                    "First Name field should be displayed");
            softAssert.assertTrue(checkoutStepOnePage.isLastNameFieldDisplayed(),
                    "Last Name field should be displayed");
            softAssert.assertTrue(checkoutStepOnePage.isZipCodeFieldDisplayed(),
                    "Zip Code field should be displayed");

            LoggerUtils.testPassed("TC-038: All checkout fields are displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-038 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-039: Verify checkout form First Name field accepts input - Step 1")
    @Severity(SeverityLevel.NORMAL)
    public void testFirstNameFieldAcceptsInput() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-039: Testing First Name field accepts input");

        try {
            checkoutStepOnePage.enterFirstName(TestData.FIRST_NAME);

            String firstName = checkoutStepOnePage.getFirstNameValue();
            softAssert.assertEquals(firstName, TestData.FIRST_NAME,
                    "First Name field should contain entered value");

            LoggerUtils.testPassed("TC-039: First Name field accepts input");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-039 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-040: Verify checkout form Last Name field accepts input - Step 1")
    @Severity(SeverityLevel.NORMAL)
    public void testLastNameFieldAcceptsInput() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-040: Testing Last Name field accepts input");

        try {
            checkoutStepOnePage.enterLastName(TestData.LAST_NAME);

            String lastName = checkoutStepOnePage.getLastNameValue();
            softAssert.assertEquals(lastName, TestData.LAST_NAME,
                    "Last Name field should contain entered value");

            LoggerUtils.testPassed("TC-040: Last Name field accepts input");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-040 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-041: Verify checkout form Zip Code field accepts input - Step 1")
    @Severity(SeverityLevel.NORMAL)
    public void testZipCodeFieldAcceptsInput() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-041: Testing Zip Code field accepts input");

        try {
            checkoutStepOnePage.enterZipCode(TestData.ZIP_CODE);

            String zipCode = checkoutStepOnePage.getZipCodeValue();
            softAssert.assertEquals(zipCode, TestData.ZIP_CODE,
                    "Zip Code field should contain entered value");

            LoggerUtils.testPassed("TC-041: Zip Code field accepts input");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-041 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-042: Verify Continue button on Step 1")
    @Severity(SeverityLevel.CRITICAL)
    public void testContinueButtonStep1() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-042: Testing Continue button on step 1");

        try {
            checkoutStepOnePage.fillCheckoutInformationWithDefaults();
            checkoutStepOnePage.clickContinueButton();

            softAssert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(),
                    "Should navigate to checkout step 2");
            softAssert.assertTrue(driver.getCurrentUrl().contains(TestData.CHECKOUT_STEP_TWO_URL),
                    "URL should be checkout step 2");

            LoggerUtils.testPassed("TC-042: Continue button navigates to step 2");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-042 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-043: Verify Cancel button on checkout step 1")
    @Severity(SeverityLevel.NORMAL)
    public void testCancelButtonStep1() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-043: Testing Cancel button on step 1");

        try {
            checkoutStepOnePage.clickCancelButton();

            softAssert.assertTrue(cartPage.isCartPageDisplayed(),
                    "Should navigate back to cart page");

            LoggerUtils.testPassed("TC-043: Cancel button returns to cart page");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-043 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-044: Verify checkout overview displays order items - Step 2")
    @Severity(SeverityLevel.NORMAL)
    public void testCheckoutStep2DisplaysOrderItems() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-044: Testing checkout step 2 displays order items");

        try {

            checkoutStepOnePage.fillCheckoutInformationWithDefaults();
            checkoutStepOnePage.clickContinueButton();

            softAssert.assertTrue(checkoutStepTwoPage.isCheckoutStepTwoPageDisplayed(),
                    "Checkout step 2 page should be displayed");

            boolean backpackInOrder = checkoutStepTwoPage.isOrderItemDisplayed(TestData.BACKPACK_PRODUCT);
            boolean bikeLightInOrder = checkoutStepTwoPage.isOrderItemDisplayed(TestData.BIKE_LIGHT_PRODUCT);

            softAssert.assertTrue(backpackInOrder, "Backpack should be in order");
            softAssert.assertTrue(bikeLightInOrder, "Bike light should be in order");

            LoggerUtils.testPassed("TC-044: Order items are displayed on step 2");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-044 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-045: Verify payment information is displayed - Step 2")
    @Severity(SeverityLevel.NORMAL)
    public void testPaymentInformationDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-045: Testing payment information is displayed");

        try {

            checkoutStepOnePage.fillCheckoutInformationWithDefaults();
            checkoutStepOnePage.clickContinueButton();

            softAssert.assertTrue(checkoutStepTwoPage.isPaymentInfoDisplayed(),
                    "Payment information should be displayed");

            String paymentInfo = checkoutStepTwoPage.getPaymentInformation();
            softAssert.assertTrue(paymentInfo.contains("SauceCard"),
                    "Payment info should contain SauceCard");

            LoggerUtils.testPassed("TC-045: Payment information is displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-045 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-046: Verify shipping information is displayed - Step 2")
    @Severity(SeverityLevel.NORMAL)
    public void testShippingInformationDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-046: Testing shipping information is displayed");

        try {

            checkoutStepOnePage.fillCheckoutInformationWithDefaults();
            checkoutStepOnePage.clickContinueButton();

            softAssert.assertTrue(checkoutStepTwoPage.isShippingInfoDisplayed(),
                    "Shipping information should be displayed");

            String shippingInfo = checkoutStepTwoPage.getShippingInformation();
            softAssert.assertTrue(shippingInfo.contains("Free Pony Express"),
                    "Shipping info should contain Free Pony Express");

            LoggerUtils.testPassed("TC-046: Shipping information is displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-046 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-047: Verify price breakdown is displayed - Step 2")
    @Severity(SeverityLevel.NORMAL)
    public void testPriceBreakdownDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-047: Testing price breakdown is displayed");

        try {

            checkoutStepOnePage.fillCheckoutInformationWithDefaults();
            checkoutStepOnePage.clickContinueButton();

            softAssert.assertTrue(checkoutStepTwoPage.isTotalPriceDisplayed(),
                    "Total price should be displayed");

            String totalPrice = checkoutStepTwoPage.getTotalPrice();
            softAssert.assertFalse(totalPrice.isEmpty(),
                    "Total price should not be empty");

            LoggerUtils.testPassed("TC-047: Price breakdown is displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-047 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-048: Verify Finish button completes order - Step 2")
    @Severity(SeverityLevel.CRITICAL)
    public void testFinishButtonCompletesOrder() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-048: Testing Finish button completes order");

        try {

            checkoutStepOnePage.fillCheckoutInformationWithDefaults();
            checkoutStepOnePage.clickContinueButton();

            checkoutStepTwoPage.clickFinishButton();

            softAssert.assertTrue(orderConfirmationPage.isOrderConfirmationPageDisplayed(),
                    "Order confirmation page should be displayed");
            softAssert.assertTrue(driver.getCurrentUrl().contains(TestData.CHECKOUT_COMPLETE_URL),
                    "URL should be order confirmation page");

            LoggerUtils.testPassed("TC-048: Finish button completes order");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-048 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-049: Verify Cancel button on checkout step 2")
    @Severity(SeverityLevel.NORMAL)
    public void testCancelButtonStep2() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-049: Testing Cancel button on step 2");

        try {

            checkoutStepOnePage.fillCheckoutInformationWithDefaults();
            checkoutStepOnePage.clickContinueButton();

            checkoutStepTwoPage.clickCancelButton();

            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Should navigate back to products page");

            LoggerUtils.testPassed("TC-049: Cancel button returns to products page");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-049 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }
}

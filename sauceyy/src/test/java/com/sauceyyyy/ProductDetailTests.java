package com.sauceyyyy;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import com.sauceyyyy.pages.ProductDetailPage;
import com.sauceyyyy.pages.ProductsPage;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;

@Listeners({com.sauceyyyy.listeners.TestListener.class, AllureTestNg.class})
public class ProductDetailTests extends BaseTest {

    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;

    @BeforeMethod
    public void productDetailTestSetup() {
        loginPage.navigateToLoginPage();
        loginPage.login(TestData.STANDARD_USER, TestData.DEFAULT_PASSWORD);
        productsPage = new ProductsPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        productsPage.navigateToProductsPage();
    }

    @Test(description = "TC-031: Verify product detail page displays product name")
    @Severity(SeverityLevel.NORMAL)

    public void testProductNameDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-031: Verifying product name on detail page");

        try {
            productsPage.clickProductByName(TestData.BACKPACK_PRODUCT);
            String actualName = productDetailPage.getProductName();
            softAssert.assertEquals(actualName, TestData.BACKPACK_PRODUCT, "Product name mismatch");
            LoggerUtils.testPassed("TC-031: Product name verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-031 failed: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-032: Verify product detail page displays product image")
    @Severity(SeverityLevel.NORMAL)
    public void testProductImageDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-032: Verifying product image on detail page");

        try {
            productsPage.clickProductByName(TestData.BACKPACK_PRODUCT);
            boolean imageDisplayed = productDetailPage.isProductImageDisplayed();
            softAssert.assertTrue(imageDisplayed, "Product image should be displayed");
            LoggerUtils.testPassed("TC-032: Product image verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-032 failed: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-033: Verify product detail page displays description")
    @Severity(SeverityLevel.NORMAL)
    public void testProductDescriptionDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-033: Verifying product description");

        try {
            productsPage.clickProductByName(TestData.BACKPACK_PRODUCT);
            String description = productDetailPage.getProductDescription();
            softAssert.assertFalse(description.isEmpty(), "Product description should not be empty");
            LoggerUtils.testPassed("TC-033: Product description verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-033 failed: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-034: Verify product detail page displays price")
    @Severity(SeverityLevel.NORMAL)
    public void testProductPriceDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-034: Verifying product price");

        try {
            productsPage.clickProductByName(TestData.BACKPACK_PRODUCT);
            String price = productDetailPage.getProductPrice();
            softAssert.assertEquals(price, "$29.99", "Product price mismatch");
            LoggerUtils.testPassed("TC-034: Product price verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-034 failed: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-035: Verify Add to Cart button on detail page")
    @Severity(SeverityLevel.NORMAL)
    public void testAddToCartButton() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-035: Verifying Add to Cart button functionality");

        try {
            productsPage.clickProductByName(TestData.BACKPACK_PRODUCT);
            productDetailPage.clickAddToCart();
            String buttonText = productDetailPage.getAddToCartButtonText();
            softAssert.assertEquals(buttonText, "Remove", "Button should change to Remove after clicking Add to Cart");
            LoggerUtils.testPassed("TC-035: Add to Cart button verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-035 failed: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-036: Verify Back to Products link")
    @Severity(SeverityLevel.NORMAL)
    public void testBackToProductsLink() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-036: Verifying Back to Products link");

        try {
            productsPage.clickProductByName(TestData.BACKPACK_PRODUCT);
            productDetailPage.clickBackToProducts();
            boolean isProductsPage = productsPage.isProductsPageDisplayed();
            softAssert.assertTrue(isProductsPage, "Should navigate back to products page");
            LoggerUtils.testPassed("TC-036: Back to Products link verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-036 failed: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-037: Verify all product details are consistent with listing")
    @Severity(SeverityLevel.NORMAL)
    public void testProductDetailsConsistent() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-037: Verifying product details consistency with listing");

        try {
            String listedName = productsPage.getProductNameFromListing(TestData.BACKPACK_PRODUCT);
            String listedPrice = productsPage.getProductPriceFromListing(TestData.BACKPACK_PRODUCT);
            String listedDescription = productsPage.getProductDescriptionFromListing(TestData.BACKPACK_PRODUCT);

            productsPage.clickProductByName(TestData.BACKPACK_PRODUCT);

            String detailName = productDetailPage.getProductName();
            String detailPrice = productDetailPage.getProductPrice();
            String detailDescription = productDetailPage.getProductDescription();

            softAssert.assertEquals(detailName, listedName, "Product name mismatch");
            softAssert.assertEquals(detailPrice, listedPrice, "Product price mismatch");
            softAssert.assertEquals(detailDescription, listedDescription, "Product description mismatch");

            LoggerUtils.testPassed("TC-037: Product details consistent with listing verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-037 failed: " + e.getMessage());
            softAssert.fail("Exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }
}

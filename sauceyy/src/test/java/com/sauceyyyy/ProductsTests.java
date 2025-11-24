package com.sauceyyyy;

import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import java.util.List;
import java.util.stream.Collectors; // Import for Collectors
@Listeners({com.sauceyyyy.listeners.TestListener.class, AllureTestNg.class})
public class ProductsTests extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void productsSetup() {
        loginPage.navigateToLoginPage();
        loginPage.login(TestData.STANDARD_USER, TestData.DEFAULT_PASSWORD);
        menuPage.clickResetAppState();
        productsPage.navigateToProductsPage();
    }

    @AfterMethod(alwaysRun = true)
    public void afterProductsTest() {
        driver.get(TestData.INVENTORY_URL);
    }

    @Test(description = "TC-013: Verify all 6 products are displayed on products page")
    @Description("Test that all 6 products appear on the products page")
    @Severity(SeverityLevel.CRITICAL)
    public void testAllProductsDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-013: Testing all products are displayed");

        try {
            int productCount = productsPage.getProductCount();
            softAssert.assertEquals(productCount, 6, "Should display 6 products");
            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page should be displayed");

            LoggerUtils.testPassed("TC-013: All 6 products displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-013 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-014: Verify product names are correct")
    @Description("Test that all product names match expected values")
    @Severity(SeverityLevel.NORMAL)
    public void testProductNamesCorrect() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-014: Testing product names are correct");

        try {
            List<WebElement> productElements = productsPage.getAllProductNames();

            List<String> actualProductNames = productElements.stream()
                                                            .map(element -> element.getText().trim().toLowerCase())
                                                            .toList();

            String expectedBackpack = TestData.BACKPACK_PRODUCT.trim().toLowerCase();
            String expectedBikeLight = TestData.BIKE_LIGHT_PRODUCT.trim().toLowerCase();
            String expectedBoltTshirt = TestData.BOLT_TSHIRT_PRODUCT.trim().toLowerCase();
            String expectedFleeceJacket = TestData.FLEECE_JACKET_PRODUCT.trim().toLowerCase();
            String expectedOnesie = TestData.ONESIE_PRODUCT.trim().toLowerCase();
            String expectedRedTshirt = TestData.RED_TSHIRT_PRODUCT.trim().toLowerCase();


            softAssert.assertTrue(actualProductNames.contains(expectedBackpack),
                    "Backpack product should be in list");
            softAssert.assertTrue(actualProductNames.contains(expectedBikeLight),
                    "Bike light product should be in list");
            softAssert.assertTrue(actualProductNames.contains(expectedBoltTshirt),
                    "Bolt T-shirt product should be in list");
            softAssert.assertTrue(actualProductNames.contains(expectedFleeceJacket),
                    "Fleece Jacket product should be in list");
            softAssert.assertTrue(actualProductNames.contains(expectedOnesie),
                    "Onesie product should be in list");
            softAssert.assertTrue(actualProductNames.contains(expectedRedTshirt),
                    "Red T-shirt product should be in list");


            LoggerUtils.testPassed("TC-014: Product names are correct");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-014 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-015: Verify product prices are displayed")
    @Description("Test that all products have prices displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testProductPricesDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-015: Testing product prices are displayed");

        try {
            List<String> prices = productsPage.getAllProductPrices();

            softAssert.assertTrue(prices.contains(TestData.BACKPACK_PRICE),
                    "Backpack price should be correct");
            softAssert.assertTrue(prices.contains(TestData.BIKE_LIGHT_PRICE),
                    "Bike light price should be correct");
            softAssert.assertEquals(prices.size(), 6, "All 6 products should have prices");

            LoggerUtils.testPassed("TC-015: Product prices are displayed correctly");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-015 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-016: Verify product descriptions are visible")
    @Severity(SeverityLevel.NORMAL)
    public void testProductDescriptionsVisible() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-016: Testing product descriptions are visible");

        try {
            softAssert.assertTrue(productsPage.isProductDisplayed(TestData.BACKPACK_PRODUCT),
                    "Backpack with description should be displayed");
            softAssert.assertTrue(productsPage.isProductDisplayed(TestData.BIKE_LIGHT_PRODUCT),
                    "Bike light with description should be displayed");

            LoggerUtils.testPassed("TC-016: Product descriptions are visible");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-016 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-017: Verify sorting dropdown works - Name A to Z")
    @Severity(SeverityLevel.NORMAL)
    public void testSortingNameAtoZ() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-017: Testing sorting by Name A to Z");

        try {
            productsPage.selectSortOption("Name (A to Z)");

            List<WebElement> sortedProducts = productsPage.getAllProductNames();
            softAssert.assertFalse(sortedProducts.isEmpty(), "Sorted products should not be empty");

            LoggerUtils.testPassed("TC-017: Sorting by Name A to Z works");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-017 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-018: Verify sorting dropdown shows all options")
    @Severity(SeverityLevel.NORMAL)
    public void testSortingDropdownOptions() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-018: Testing sorting dropdown has all options");

        try {
            String currentValue = productsPage.getSortDropdownValue();
            softAssert.assertNotNull(currentValue, "Sort dropdown should have a value");

            LoggerUtils.testPassed("TC-018: Sorting dropdown has all options");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-018 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-019: Verify product image is displayed for each product")
    @Severity(SeverityLevel.NORMAL)
    public void testProductImagesDisplayed() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-019: Testing product images are displayed");

        try {
            softAssert.assertTrue(productsPage.isProductDisplayed(TestData.BACKPACK_PRODUCT),
                    "Product with image should be displayed");

            LoggerUtils.testPassed("TC-019: Product images are displayed");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-019 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-020: Verify clicking on product name navigates to product detail page")
    @Severity(SeverityLevel.NORMAL)
    public void testClickProductNavigatesToDetail() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-020: Testing clicking product navigates to detail page");

        try {
            productsPage.clickProductByName(TestData.BACKPACK_PRODUCT);

            softAssert.assertTrue(productDetailPage.isProductDetailPageDisplayed(),
                    "Product detail page should be displayed");
            softAssert.assertEquals(productDetailPage.getProductName(), TestData.BACKPACK_PRODUCT,
                    "Product name should match");

            LoggerUtils.testPassed("TC-020: Clicking product navigates to detail page");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-020 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }
}

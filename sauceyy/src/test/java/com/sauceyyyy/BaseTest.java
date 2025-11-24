package com.sauceyyyy;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import com.sauceyyyy.config.DriverFactory;
import com.sauceyyyy.pages.*;
import com.sauceyyyy.utils.LoggerUtils;

public class BaseTest {

    protected static WebDriver driver;

    // Page Objects
    protected static LoginPage loginPage;
    protected static ProductsPage productsPage;
    protected static ProductDetailPage productDetailPage;
    protected static CartPage cartPage;
    protected static CheckoutStepOnePage checkoutStepOnePage;
    protected static CheckoutStepTwoPage checkoutStepTwoPage;
    protected static OrderConfirmationPage orderConfirmationPage;
    protected static MenuPage menuPage;

    @BeforeSuite(alwaysRun = true)
    public void suiteSetUp() {
        LoggerUtils.info("========== SUITE SETUP STARTED ==========");
        driver = DriverFactory.initializeDriver();

        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        orderConfirmationPage = new OrderConfirmationPage(driver);
        menuPage = new MenuPage(driver);

        LoggerUtils.info("All Page Objects initialized successfully");
        LoggerUtils.info("========== SUITE SETUP COMPLETED ==========");
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTearDown() {
        LoggerUtils.info("========== SUITE TEARDOWN STARTED ==========");
        if (driver != null) {
            DriverFactory.quitDriver();
            LoggerUtils.info("WebDriver quit successfully");
        }
        LoggerUtils.info("========== SUITE TEARDOWN COMPLETED ==========");
    }
}

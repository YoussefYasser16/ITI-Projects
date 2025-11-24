package com.sauceyyyy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import com.sauceyyyy.utils.WaitUtils;

public class CheckoutStepOnePage {

    private WebDriver driver;
    private WaitUtils waitUtils;


    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipCodeField = By.id("postal-code");
    private final By cancelButton = By.id("cancel");
    private final By continueButton = By.id("continue");
    private final By checkoutTitle = By.cssSelector("[data-test='title']");

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void navigateToCheckoutStepOne() {
        LoggerUtils.info("Navigating to checkout step one: " + TestData.CHECKOUT_STEP_ONE_URL);
        driver.navigate().to(TestData.CHECKOUT_STEP_ONE_URL);
    }

    public void enterFirstName(String firstName) {
        LoggerUtils.info("Entering first name: " + firstName);
        var field = waitUtils.waitForElementToBeVisible(firstNameField);
        field.clear();
        field.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        LoggerUtils.info("Entering last name: " + lastName);
        var field = waitUtils.waitForElementToBeVisible(lastNameField);
        field.clear();
        field.sendKeys(lastName);
    }

    public void enterZipCode(String zipCode) {
        LoggerUtils.info("Entering zip code: " + zipCode);
        var field = waitUtils.waitForElementToBeVisible(zipCodeField);
        field.clear();
        field.sendKeys(zipCode);
    }

    public void fillCheckoutInformation(String firstName, String lastName, String zipCode) {
        LoggerUtils.info("Filling checkout information");
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
    }

    public void fillCheckoutInformationWithDefaults() {
        LoggerUtils.info("Filling checkout information with default values");
        fillCheckoutInformation(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.ZIP_CODE);
    }

    public void clickCancelButton() {
        LoggerUtils.info("Clicking Cancel button");
        waitUtils.waitForElementToBeClickable(cancelButton).click();
    }

    public void clickContinueButton() {
        LoggerUtils.info("Clicking Continue button");
        waitUtils.waitForElementToBeClickable(continueButton).click();
    }


    public String getFirstNameValue() {
        LoggerUtils.info("Getting first name value");
        try {
            return waitUtils.waitForElementToBeVisible(firstNameField).getAttribute("value");
        } catch (Exception e) {
            LoggerUtils.error("Could not get first name value: " + e.getMessage());
            return "";
        }
    }

    public String getLastNameValue() {
        LoggerUtils.info("Getting last name value");
        try {
            return waitUtils.waitForElementToBeVisible(lastNameField).getAttribute("value");
        } catch (Exception e) {
            LoggerUtils.error("Could not get last name value: " + e.getMessage());
            return "";
        }
    }

    public String getZipCodeValue() {
        LoggerUtils.info("Getting zip code value");
        try {
            return waitUtils.waitForElementToBeVisible(zipCodeField).getAttribute("value");
        } catch (Exception e) {
            LoggerUtils.error("Could not get zip code value: " + e.getMessage());
            return "";
        }
    }

    public boolean isFirstNameFieldDisplayed() {
        LoggerUtils.info("Verifying first name field is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(firstNameField).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("First name field not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isLastNameFieldDisplayed() {
        LoggerUtils.info("Verifying last name field is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(lastNameField).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Last name field not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isZipCodeFieldDisplayed() {
        LoggerUtils.info("Verifying zip code field is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(zipCodeField).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Zip code field not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isCheckoutStepOnePageDisplayed() {
        LoggerUtils.info("Verifying checkout step one page is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(checkoutTitle).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Checkout step one page not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isContinueButtonDisplayed() {
        LoggerUtils.info("Verifying continue button is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(continueButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Continue button not displayed: " + e.getMessage());
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
}

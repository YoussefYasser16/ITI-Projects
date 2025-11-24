package com.sauceyyyy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import com.sauceyyyy.utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessageContainer = By.className("error-message-container");
    private final By errorCloseButton = By.className("error-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    public void navigateToLoginPage() {
        LoggerUtils.info("Navigating to login page: " + TestData.BASE_URL);
        driver.navigate().to(TestData.BASE_URL);
    }

    public void enterUsername(String username) {
        LoggerUtils.info("Entering username: " + username);
        WebElement field = waitUtils.waitForElementToBeVisible(usernameField);
        field.clear();
        field.sendKeys(username);
    }

    public void enterPassword(String password) {
        LoggerUtils.info("Entering password");
        WebElement field = waitUtils.waitForElementToBeVisible(passwordField);
        field.clear();
        field.sendKeys(password);
    }

    public void clickLoginButton() {
        LoggerUtils.info("Clicking Login button");
        waitUtils.waitForElementToBeClickable(loginButton).click();
    }

    public void login(String username, String password) {
        LoggerUtils.info("Starting login with username: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        LoggerUtils.info("Attempting to get error message");
        try {
            // The error message is inside an <h3> tag within the container
            WebElement errorContainer = waitUtils.waitForElementToBeVisible(errorMessageContainer);
            return errorContainer.findElement(By.tagName("h3")).getText();
        } catch (Exception e) {
            LoggerUtils.warn("Error message not found: " + e.getMessage());
            return "";
        }
    }

    public void closeErrorMessage() {
        LoggerUtils.info("Attempting to close error message");
        try {
            waitUtils.waitForElementToBeClickable(errorCloseButton).click();
        } catch (Exception e) {
            LoggerUtils.warn("Error close button not found: " + e.getMessage());
        }
    }


    public boolean isLoginPageDisplayed() {
        LoggerUtils.info("Checking if login page is displayed");
        try {
            return waitUtils.waitForElementToBeVisible(loginButton).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Login page not displayed: " + e.getMessage());
            return false;
        }
    }

    public boolean isUsernameFieldPresent() {
        LoggerUtils.info("Verifying username field is present");
        try {
            return waitUtils.waitForElementToBeVisible(usernameField).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Username field not present: " + e.getMessage());
            return false;
        }
    }

    public boolean isPasswordFieldPresent() { // Re-added method
        LoggerUtils.info("Verifying password field is present");
        try {
            return waitUtils.waitForElementToBeVisible(passwordField).isDisplayed();
        } catch (Exception e) {
            LoggerUtils.error("Password field not present: " + e.getMessage());
            return false;
        }
    }
}

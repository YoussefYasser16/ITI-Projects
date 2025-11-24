package com.sauceyyyy;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;

@Listeners({com.sauceyyyy.listeners.TestListener.class, AllureTestNg.class})
public class LoginTests extends BaseTest {

    @BeforeMethod
    public void loginSetup() {
        loginPage.navigateToLoginPage();
    }

    @AfterMethod
    public void afterLoginTest() {
        driver.manage().deleteAllCookies();
    }

    @Test(description = "TC-001: Verify successful login with standard_user")
    @Description("Test login functionality with valid standard_user credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithStandardUser() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-001: Testing login with standard_user");

        try {
            softAssert.assertTrue(loginPage.isLoginPageDisplayed(),
                    "Login page should be displayed");

            loginPage.login(TestData.STANDARD_USER, TestData.DEFAULT_PASSWORD);

            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page should be displayed after login");
            softAssert.assertTrue(driver.getCurrentUrl().contains(TestData.INVENTORY_URL),
                    "URL should be inventory page");

            LoggerUtils.testPassed("TC-001: Login with standard_user successful");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-001 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-002: Verify login fails with locked_out_user")
    @Description("Test that locked_out_user cannot login and error message is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithLockedOutUser() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-002: Testing login with locked_out_user");

        try {
            loginPage.login(TestData.LOCKED_OUT_USER, TestData.DEFAULT_PASSWORD);

            String errorMessage = loginPage.getErrorMessage();
            softAssert.assertTrue(errorMessage.contains(TestData.LOCKED_OUT_ERROR),
                    "Error message should indicate user is locked out");
            softAssert.assertTrue(loginPage.isLoginPageDisplayed(),
                    "Should remain on login page");

            LoggerUtils.testPassed("TC-002: Locked out user error verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-002 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-003: Verify successful login with problem_user")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithProblemUser() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-003: Testing login with problem_user");

        try {
            loginPage.login(TestData.PROBLEM_USER, TestData.DEFAULT_PASSWORD);

            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page should be displayed");

            LoggerUtils.testPassed("TC-003: Login with problem_user successful");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-003 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-004: Verify login with performance_glitch_user")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithPerformanceGlitchUser() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-004: Testing login with performance_glitch_user");

        try {
            loginPage.login(TestData.PERFORMANCE_GLITCH_USER, TestData.DEFAULT_PASSWORD);

            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page should be displayed");

            LoggerUtils.testPassed("TC-004: Login with performance_glitch_user successful");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-004 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-005: Verify successful login with error_user")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithErrorUser() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-005: Testing login with error_user");

        try {
            loginPage.login(TestData.ERROR_USER, TestData.DEFAULT_PASSWORD);

            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page should be displayed");

            LoggerUtils.testPassed("TC-005: Login with error_user successful");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-005 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-006: Verify successful login with visual_user")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithVisualUser() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-006: Testing login with visual_user");

        try {
            loginPage.login(TestData.VISUAL_USER, TestData.DEFAULT_PASSWORD);

            softAssert.assertTrue(productsPage.isProductsPageDisplayed(),
                    "Products page should be displayed");

            LoggerUtils.testPassed("TC-006: Login with visual_user successful");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-006 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-007: Verify login fails with invalid username")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithInvalidUsername() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-007: Testing login with invalid username");

        try {
            loginPage.login("invalid_user", TestData.DEFAULT_PASSWORD);

            String errorMessage = loginPage.getErrorMessage();
            softAssert.assertTrue(errorMessage.contains(TestData.INVALID_CREDENTIALS_ERROR),
                    "Error message should indicate invalid credentials");

            LoggerUtils.testPassed("TC-007: Invalid username error verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-007 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-008: Verify login fails with incorrect password")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithIncorrectPassword() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-008: Testing login with incorrect password");

        try {
            loginPage.login(TestData.STANDARD_USER, "wrong_password");

            String errorMessage = loginPage.getErrorMessage();
            softAssert.assertTrue(errorMessage.contains(TestData.INVALID_CREDENTIALS_ERROR),
                    "Error message should indicate invalid credentials");

            LoggerUtils.testPassed("TC-008: Incorrect password error verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-008 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-009: Verify login fails with empty username")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyUsername() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-009: Testing login with empty username");

        try {
            loginPage.enterPassword(TestData.DEFAULT_PASSWORD);
            loginPage.clickLoginButton();

            String errorMessage = loginPage.getErrorMessage();
            softAssert.assertTrue(errorMessage.contains(TestData.USERNAME_REQUIRED_ERROR),
                    "Error message should indicate username is required");

            LoggerUtils.testPassed("TC-009: Empty username error verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-009 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-010: Verify login fails with empty password")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyPassword() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-010: Testing login with empty password");

        try {
            loginPage.enterUsername(TestData.STANDARD_USER);
            loginPage.clickLoginButton();

            String errorMessage = loginPage.getErrorMessage();
            softAssert.assertTrue(errorMessage.contains(TestData.PASSWORD_REQUIRED_ERROR),
                    "Error message should indicate password is required");

            LoggerUtils.testPassed("TC-010: Empty password error verified");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-010 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-011: Verify close error message functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testCloseErrorMessage() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-011: Testing close error message");

        try {
            loginPage.login("invalid", "invalid");

            String errorMessage = loginPage.getErrorMessage();
            softAssert.assertFalse(errorMessage.isEmpty(),
                    "Error message should be displayed");

            loginPage.closeErrorMessage();

            LoggerUtils.testPassed("TC-011: Error message closed successfully");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-011 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(description = "TC-012: Verify username and password fields are visible")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginFieldsVisible() {
        SoftAssert softAssert = new SoftAssert();
        LoggerUtils.info("TC-012: Testing login fields visibility");

        try {
            softAssert.assertTrue(loginPage.isUsernameFieldPresent(),
                    "Username field should be present");
            softAssert.assertTrue(loginPage.isPasswordFieldPresent(),
                    "Password field should be present");

            LoggerUtils.testPassed("TC-012: Login fields are visible");
        } catch (Exception e) {
            LoggerUtils.testFailed("TC-012 failed: " + e.getMessage());
            softAssert.fail("Test failed with exception: " + e.getMessage());
        }
        softAssert.assertAll();
    }
}

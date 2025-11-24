package com.sauceyyyy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; // Import WebElement
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.sauceyyyy.utils.LoggerUtils;
import com.sauceyyyy.utils.WaitUtils;

public class MenuPage {

    private WebDriver driver;
    private WaitUtils waitUtils;

    private final By menuButton = By.className("bm-burger-button");
    private final By closeMenuButton = By.id("react-burger-cross-btn");
    private final By allItemsLink = By.xpath("//a[contains(text(), 'All Items')]");
    private final By aboutLink = By.xpath("//a[contains(text(), 'About')]");
    private final By logoutLink = By.xpath("//a[contains(text(), 'Logout')]");
    private final By resetAppStateLink = By.id("reset_sidebar_link"); // CORRECTED ID
    private final By menuContainer = By.xpath("//div[@class='bm-menu-wrap' and @aria-hidden='false']");

    public MenuPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }


    public void openMenu() {
        LoggerUtils.info("Opening menu");
        waitUtils.waitForElementToBeClickable(menuButton).click();

        waitUtils.getWait().until(ExpectedConditions.visibilityOfElementLocated(menuContainer));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void closeMenu() {
        LoggerUtils.info("Closing menu");
        try {
            waitUtils.waitForElementToBeClickable(closeMenuButton).click();
        } catch (Exception e) {
            LoggerUtils.warn("Close menu button not found or not clickable: " + e.getMessage());
        }
    }

    public void clickAllItems() {
        LoggerUtils.info("Clicking All Items link");
        openMenu();
        waitUtils.waitForElementToBeClickable(allItemsLink).click();
    }

    public void clickAbout() {
        LoggerUtils.info("Clicking About link");
        openMenu();
        waitUtils.waitForElementToBeClickable(aboutLink).click();
    }

    public void clickLogout() {
        LoggerUtils.info("Clicking Logout link");
        openMenu();
        waitUtils.waitForElementToBeClickable(logoutLink).click();
    }

    public void clickResetAppState() {
        LoggerUtils.info("Clicking Reset App State link");
        openMenu();

        WebElement resetLink = waitUtils.waitForElementToBeVisible(resetAppStateLink);
        resetLink.click();
        closeMenu();
    }
}

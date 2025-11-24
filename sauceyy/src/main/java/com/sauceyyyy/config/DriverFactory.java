package com.sauceyyyy.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.sauceyyyy.utils.TestData;
import com.sauceyyyy.utils.LoggerUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito"); // Run in incognito mode


        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);


        options.setExperimentalOption("excludeSwitches",
                Arrays.asList("enable-automation", "enable-logging"));

        return options;
    }

    public static WebDriver initializeDriver() {
        String browser = "chrome";
        LoggerUtils.info("Initializing WebDriver for browser: " + browser);

        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver(getChromeOptions()));
                    LoggerUtils.info("Chrome browser initialized");
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    LoggerUtils.info("Firefox browser initialized");
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    LoggerUtils.info("Edge browser initialized");
                    break;

                default:
                    LoggerUtils.error("Browser not supported: " + browser);
                    throw new IllegalArgumentException("Browser not supported: " + browser);
            }


            driver.get().manage().timeouts().implicitlyWait(
                    java.time.Duration.ofSeconds(TestData.IMPLICIT_WAIT_TIMEOUT)
            );
            LoggerUtils.info("Implicit wait set to: " + TestData.IMPLICIT_WAIT_TIMEOUT + " seconds");


            driver.get().manage().window().maximize();
            LoggerUtils.info("Browser window maximized");
        }

        return driver.get();
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            LoggerUtils.warn("Driver is null, initializing...");
            return initializeDriver();
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            LoggerUtils.info("WebDriver quit successfully");
            driver.remove();
        }
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            driver.get().close();
            LoggerUtils.info("Browser window closed");
        }
    }
}

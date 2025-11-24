package com.sauceyyyy.utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {

    private static final Logger logger = LogManager.getLogger(LoggerUtils.class);

    public static void info(String message) {
        logger.info(message);
        Allure.step(message);
    }

    public static void error(String message) {
        logger.error(message);
        Allure.step("ERROR: " + message);
    }

    public static void warn(String message) {
        logger.warn(message);
        Allure.step("WARNING: " + message);
    }

    public static void debug(String message) {

        logger.debug(message);
    }

    public static void testFailed(String message) {
        logger.error("TEST FAILED: " + message);
        Allure.step("TEST FAILED: " + message);
    }

    public static void testPassed(String message) {
        logger.info("TEST PASSED: " + message);
        Allure.step("TEST PASSED: " + message);
    }
}

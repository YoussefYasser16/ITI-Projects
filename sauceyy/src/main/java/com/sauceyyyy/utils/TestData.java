package com.sauceyyyy.utils;

public class TestData {

    // ===== URLS =====
    public static final String BASE_URL = "https://www.saucedemo.com";
    public static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";
    public static final String CART_URL = "https://www.saucedemo.com/cart.html";
    public static final String CHECKOUT_STEP_ONE_URL = "https://www.saucedemo.com/checkout-step-one.html";
    public static final String CHECKOUT_STEP_TWO_URL = "https://www.saucedemo.com/checkout-step-two.html";
    public static final String CHECKOUT_COMPLETE_URL = "https://www.saucedemo.com/checkout-complete.html";

    // ===== USER CREDENTIALS =====
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    public static final String ERROR_USER = "error_user";
    public static final String VISUAL_USER = "visual_user";
    public static final String DEFAULT_PASSWORD = "secret_sauce";

    // ===== EXPECTED ERROR MESSAGES =====
    public static final String LOCKED_OUT_ERROR = "locked out";
    public static final String INVALID_CREDENTIALS_ERROR = "Username and password";
    public static final String USERNAME_REQUIRED_ERROR = "Username is required";
    public static final String PASSWORD_REQUIRED_ERROR = "Password is required";
    public static final String ORDER_SUCCESS_MESSAGE = "Thank you for your order!";

    // ===== PRODUCT NAMES =====
    public static final String BACKPACK_PRODUCT = "Sauce Labs Backpack";
    public static final String BIKE_LIGHT_PRODUCT = "Sauce Labs Bike Light";
    public static final String BOLT_TSHIRT_PRODUCT = "Sauce Labs Bolt T-Shirt";
    public static final String FLEECE_JACKET_PRODUCT = "Sauce Labs Fleece Jacket";
    public static final String ONESIE_PRODUCT = "Sauce Labs Onesie";
    public static final String RED_TSHIRT_PRODUCT = "Test.allTheThings() T-Shirt (Red)";

    // ===== PRODUCT PRICES =====
    public static final String BACKPACK_PRICE = "$29.99";
    public static final String BIKE_LIGHT_PRICE = "$9.99";
    public static final String BOLT_TSHIRT_PRICE = "$15.99";
    public static final String FLEECE_JACKET_PRICE = "$49.99";
    public static final String ONESIE_PRICE = "$7.99";

    // ===== CHECKOUT INFO =====
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String ZIP_CODE = "12345";

    // ===== TIMEOUTS (seconds) =====
    public static final int IMPLICIT_WAIT_TIMEOUT = 5;
    public static final int EXPLICIT_WAIT_TIMEOUT = 7;
    public static final int FLUENT_WAIT_TIMEOUT = 7;
    public static final long POLLING_INTERVAL = 300;
}

package com.epam.pages.locators;

import org.openqa.selenium.By;

public final class SauceDemoLoginPageLocators {

    public static final By USERNAME = By.id("user-name");
    public static final By PASSWORD = By.id("password");
    public static final By LOGIN_BUTTON = By.id("login-button");
    public static final By OK_POPUP_BUTTON = By.xpath("//button[normalize-space()='OK' or normalize-space()='Ok' or normalize-space()='ok']");

    private SauceDemoLoginPageLocators() {
    }
}

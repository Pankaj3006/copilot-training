package com.epam.pages.locators;

import org.openqa.selenium.By;

public final class OrangeHrmLoginPageLocators {

    public static final By USERNAME = By.name("username");
    public static final By PASSWORD = By.name("password");
    public static final By LOGIN_BUTTON = By.xpath("//button[@type='submit']");
    public static final By ORANGE_HRM_INC_LINK = By.xpath("//a[contains(normalize-space(.),'OrangeHRM, Inc')]");

    private OrangeHrmLoginPageLocators() {
    }
}

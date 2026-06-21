package com.epam.pages;

import com.epam.pages.locators.SauceDemoCheckoutInfoPageLocators;
import org.openqa.selenium.WebDriver;

public class SauceDemoCheckoutInfoPage extends BasePage {

    public SauceDemoCheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public SauceDemoCheckoutInfoPage enterCheckoutDetails(String firstName, String lastName, String postalCode) {
        waitForVisible(SauceDemoCheckoutInfoPageLocators.FIRST_NAME).sendKeys(firstName);
        waitForVisible(SauceDemoCheckoutInfoPageLocators.LAST_NAME).sendKeys(lastName);
        waitForVisible(SauceDemoCheckoutInfoPageLocators.POSTAL_CODE).sendKeys(postalCode);
        return this;
    }

    public SauceDemoCheckoutOverviewPage continueCheckout() {
        waitForVisible(SauceDemoCheckoutInfoPageLocators.CONTINUE_BUTTON).click();
        return new SauceDemoCheckoutOverviewPage(driver);
    }
}

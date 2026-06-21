package com.epam.pages;

import com.epam.pages.locators.SauceDemoCartPageLocators;
import org.openqa.selenium.WebDriver;

public class SauceDemoCartPage extends BasePage {

    public SauceDemoCartPage(WebDriver driver) {
        super(driver);
    }

    public SauceDemoCheckoutInfoPage proceedToCheckout() {
        waitForVisible(SauceDemoCartPageLocators.CHECKOUT_BUTTON).click();
        return new SauceDemoCheckoutInfoPage(driver);
    }
}

package com.epam.pages;

import com.epam.pages.locators.SauceDemoProductsPageLocators;
import org.openqa.selenium.WebDriver;

public class SauceDemoProductsPage extends BasePage {

    public SauceDemoProductsPage(WebDriver driver) {
        super(driver);
    }

    public SauceDemoProductsPage addBoltTShirtToCart() {
        waitForVisible(SauceDemoProductsPageLocators.ADD_BOLT_T_SHIRT).click();
        return this;
    }

    public SauceDemoCartPage goToCart() {
        waitForVisible(SauceDemoProductsPageLocators.CART_LINK).click();
        return new SauceDemoCartPage(driver);
    }
}

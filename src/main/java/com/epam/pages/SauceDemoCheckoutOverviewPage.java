package com.epam.pages;

import com.epam.pages.locators.SauceDemoCheckoutOverviewPageLocators;
import org.openqa.selenium.WebDriver;

public class SauceDemoCheckoutOverviewPage extends BasePage {

    public SauceDemoCheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public SauceDemoCheckoutCompletePage finishCheckout() {
        waitForVisible(SauceDemoCheckoutOverviewPageLocators.FINISH_BUTTON).click();
        return new SauceDemoCheckoutCompletePage(driver);
    }
}

package com.epam.pages;

import com.epam.pages.locators.SauceDemoCheckoutCompletePageLocators;
import org.openqa.selenium.WebDriver;

public class SauceDemoCheckoutCompletePage extends BasePage {

    public SauceDemoCheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getCompletionHeaderText() {
        return waitForVisible(SauceDemoCheckoutCompletePageLocators.COMPLETE_HEADER).getText().trim();
    }
}

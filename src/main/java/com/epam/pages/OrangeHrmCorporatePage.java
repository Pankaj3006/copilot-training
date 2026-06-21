package com.epam.pages;

import com.epam.pages.locators.OrangeHrmCorporatePageLocators;
import org.openqa.selenium.WebDriver;

public class OrangeHrmCorporatePage extends BasePage {

    public OrangeHrmCorporatePage(WebDriver driver) {
        super(driver);
    }

    public OrangeHrmCorporatePage waitForLoaded() {
        waitForDocumentReady();
        waitForPresent(OrangeHrmCorporatePageLocators.CONTACT_SALES);
        return this;
    }

    public OrangeHrmCorporatePage clickContactSales() {
        clickWithJs(OrangeHrmCorporatePageLocators.CONTACT_SALES);
        return this;
    }
}

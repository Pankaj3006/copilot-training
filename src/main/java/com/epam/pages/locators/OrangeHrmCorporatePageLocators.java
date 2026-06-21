package com.epam.pages.locators;

import org.openqa.selenium.By;

public final class OrangeHrmCorporatePageLocators {

    public static final By CONTACT_SALES = By.xpath("//a[contains(@href,'contact-sales')] | //*[self::a or self::button][contains(normalize-space(.),'Contact Sales')]");

    private OrangeHrmCorporatePageLocators() {
    }
}

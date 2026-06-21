package com.epam.pages.locators;

import org.openqa.selenium.By;

public final class KsrtcHomePageLocators {

    public static final String FROM_CITY_ID = "fromCity";
    public static final String TO_CITY_ID = "toCity";
    public static final By FROM_CITY = By.id(FROM_CITY_ID);
    public static final By TO_CITY = By.id(TO_CITY_ID);
    public static final By DEPARTURE_DATE = By.id("departDate");
    public static final By SEARCH_BUTTON = By.id("submitSearch");

    private KsrtcHomePageLocators() {
    }
}

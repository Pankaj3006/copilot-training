package com.epam.pages;

import com.epam.pages.locators.KsrtcHomePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class KsrtcHomePage extends BasePage {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("EEE, dd-MMM-yyyy", Locale.ENGLISH);

    public KsrtcHomePage(WebDriver driver) {
        super(driver);
    }

    public KsrtcHomePage open(String baseUrl) {
        driver.get(baseUrl);
        waitForDocumentReady();
        waitForPresent(KsrtcHomePageLocators.FROM_CITY);
        waitForPresent(KsrtcHomePageLocators.SEARCH_BUTTON);
        return this;
    }

    public KsrtcHomePage selectLeavingFrom(String city) {
        selectChosenCityOption(KsrtcHomePageLocators.FROM_CITY_ID, city);
        return this;
    }

    public KsrtcHomePage selectGoingTo(String city) {
        selectChosenCityOption(KsrtcHomePageLocators.TO_CITY_ID, city);
        return this;
    }

    public KsrtcHomePage selectDepartureDate(LocalDate departureDate) {
        setInputValueWithJs(KsrtcHomePageLocators.DEPARTURE_DATE, departureDate.format(DATE_FORMATTER));
        return this;
    }

    public SearchResultsPage searchBuses() {
        clickWithJs(KsrtcHomePageLocators.SEARCH_BUTTON);
        return new SearchResultsPage(driver).waitForResults();
    }

    private void setInputValueWithJs(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', {bubbles:true})); arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                element,
                value
        );
    }

    private void selectChosenCityOption(String selectId, String visibleText) {
        WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(selectId)));
        ((JavascriptExecutor) driver).executeScript(
                "const select = arguments[0];" +
                        "const targetText = arguments[1].trim().toLowerCase();" +
                        "const option = Array.from(select.options).find(opt => opt.text.trim().toLowerCase() === targetText || opt.text.trim().toLowerCase().includes(targetText));" +
                        "if (!option) { throw new Error('Option not found: ' + arguments[1]); }" +
                        "select.value = option.value;" +
                        "select.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "const chosen = document.getElementById(select.id + '_chosen');" +
                        "if (chosen) { const span = chosen.querySelector('.chosen-single span'); if (span) { span.textContent = option.text.trim(); } }",
                selectElement,
                visibleText
        );
    }
}

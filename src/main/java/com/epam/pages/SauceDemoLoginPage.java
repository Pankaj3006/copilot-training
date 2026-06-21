package com.epam.pages;

import com.epam.pages.locators.SauceDemoLoginPageLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SauceDemoLoginPage extends BasePage {

    public SauceDemoLoginPage(WebDriver driver) {
        super(driver);
    }

    public SauceDemoLoginPage open(String url) {
        driver.get(url);
        waitForVisible(SauceDemoLoginPageLocators.USERNAME);
        return this;
    }

    public SauceDemoProductsPage loginAs(String username, String password) {
        waitForVisible(SauceDemoLoginPageLocators.USERNAME).sendKeys(username);
        waitForVisible(SauceDemoLoginPageLocators.PASSWORD).sendKeys(password);
        waitForVisible(SauceDemoLoginPageLocators.LOGIN_BUTTON).click();
        return new SauceDemoProductsPage(driver);
    }

    public void acceptOkPopupIfPresent() {
        try {
            wait.withTimeout(Duration.ofSeconds(3)).until(d -> {
                try {
                    d.switchTo().alert();
                    return true;
                } catch (NoAlertPresentException ex) {
                    return false;
                }
            });
            Alert alert = driver.switchTo().alert();
            alert.accept();
            return;
        } catch (TimeoutException ignored) {
            // No browser alert found; try in-page OK button.
        } finally {
            wait.withTimeout(Duration.ofSeconds(20));
        }

        try {
            wait.withTimeout(Duration.ofSeconds(3))
                    .until(d -> d.findElement(SauceDemoLoginPageLocators.OK_POPUP_BUTTON).isDisplayed());
            clickWithJs(SauceDemoLoginPageLocators.OK_POPUP_BUTTON);
        } catch (TimeoutException ignored) {
            // Popup does not exist for this run.
        } finally {
            wait.withTimeout(Duration.ofSeconds(20));
        }
    }
}

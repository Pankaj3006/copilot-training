package com.epam.pages;

import com.epam.pages.locators.OrangeHrmLoginPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class OrangeHrmLoginPage extends BasePage {

    public OrangeHrmLoginPage(WebDriver driver) {
        super(driver);
    }

    public OrangeHrmLoginPage open(String url) {
        driver.get(url);
        waitForVisible(OrangeHrmLoginPageLocators.USERNAME);
        return this;
    }

    public String openOrangeHrmIncAndSwitchToChild(String parentWindow) {
        int existingWindows = driver.getWindowHandles().size();
        WebElement link = wait.until(d -> d.findElement(OrangeHrmLoginPageLocators.ORANGE_HRM_INC_LINK));
        link.click();

        wait.until(d -> d.getWindowHandles().size() > existingWindows);
        Set<String> allHandles = driver.getWindowHandles();
        for (String handle : allHandles) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                return handle;
            }
        }
        return null;
    }

    public OrangeHrmLoginPage switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
        return this;
    }

    public OrangeHrmLoginPage login(String username, String password) {
        waitForVisible(OrangeHrmLoginPageLocators.USERNAME).sendKeys(username);
        waitForVisible(OrangeHrmLoginPageLocators.PASSWORD).sendKeys(password);
        waitForVisible(OrangeHrmLoginPageLocators.LOGIN_BUTTON).click();
        return this;
    }

    public boolean isOnDashboard() {
        waitForUrlContains("/dashboard");
        return driver.getCurrentUrl().contains("/dashboard");
    }
}

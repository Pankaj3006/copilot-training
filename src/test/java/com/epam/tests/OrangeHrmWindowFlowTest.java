package com.epam.tests;

import com.aventstack.extentreports.ExtentTest;
import com.epam.core.ConfigReader;
import com.epam.enums.ConfigKey;
import com.epam.listeners.ExtentTestListener;
import org.openqa.selenium.WebDriver;
import com.epam.pages.OrangeHrmCorporatePage;
import com.epam.pages.OrangeHrmLoginPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestListener.class)
public class OrangeHrmWindowFlowTest extends BaseTest {

    @Test(description = "Validate OrangeHRM child window flow and login")
    public void shouldHandleOrangeHrmChildWindowAndLogin() {
        WebDriver driver = getDriver();
    OrangeHrmLoginPage loginPage = new OrangeHrmLoginPage(driver)
        .open(ConfigReader.get(ConfigKey.ORANGEHRM_URL));

        String parentWindow = driver.getWindowHandle();
    String childWindow = loginPage.openOrangeHrmIncAndSwitchToChild(parentWindow);

        Assert.assertNotNull(childWindow, "Expected child window to open after clicking OrangeHRM, Inc link.");

    new OrangeHrmCorporatePage(driver)
        .waitForLoaded()
        .clickContactSales();

    boolean isDashboard = loginPage
        .switchToWindow(parentWindow)
        .login(ConfigReader.get(ConfigKey.ORANGEHRM_USERNAME), ConfigReader.get(ConfigKey.ORANGEHRM_PASSWORD))
        .isOnDashboard();
    Assert.assertTrue(isDashboard, "Expected login to navigate to dashboard.");

        ExtentTest test = ExtentTestListener.getTest();
        if (test != null) {
            test.info("Successfully completed child window handling and logged in to OrangeHRM.");
        }
    }
}

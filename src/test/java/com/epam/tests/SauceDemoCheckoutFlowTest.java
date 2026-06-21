package com.epam.tests;

import com.aventstack.extentreports.ExtentTest;
import com.epam.core.ConfigReader;
import com.epam.enums.ConfigKey;
import com.epam.listeners.ExtentTestListener;
import org.openqa.selenium.WebDriver;
import com.epam.pages.SauceDemoCheckoutCompletePage;
import com.epam.pages.SauceDemoLoginPage;
import com.epam.pages.SauceDemoProductsPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestListener.class)
public class SauceDemoCheckoutFlowTest extends BaseTest {

    @Test(description = "Validate SauceDemo login, add to cart, checkout and completion")
    public void shouldCompleteSauceDemoCheckoutFlow() {
        WebDriver driver = getDriver();
    SauceDemoLoginPage loginPage = new SauceDemoLoginPage(driver)
        .open(ConfigReader.get(ConfigKey.SAUCEDEMO_URL));

    SauceDemoProductsPage productsPage = loginPage.loginAs(
        ConfigReader.get(ConfigKey.SAUCEDEMO_USERNAME),
        ConfigReader.get(ConfigKey.SAUCEDEMO_PASSWORD)
    );
    loginPage.acceptOkPopupIfPresent();

    SauceDemoCheckoutCompletePage completePage = productsPage
        .addBoltTShirtToCart()
        .goToCart()
        .proceedToCheckout()
        .enterCheckoutDetails(
            ConfigReader.get(ConfigKey.SAUCEDEMO_CHECKOUT_FIRSTNAME),
            ConfigReader.get(ConfigKey.SAUCEDEMO_CHECKOUT_LASTNAME),
            ConfigReader.get(ConfigKey.SAUCEDEMO_CHECKOUT_ZIPCODE)
        )
        .continueCheckout()
        .finishCheckout();

    String actualMessage = completePage.getCompletionHeaderText();
        Assert.assertEquals(actualMessage, "Thank you for your order!", "Expected order completion message did not match.");

        ExtentTest test = ExtentTestListener.getTest();
        if (test != null) {
            test.info("SauceDemo checkout completed successfully.");
            test.info("Assertion passed: " + actualMessage);
        }
    }
}

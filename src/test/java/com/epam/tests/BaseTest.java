package com.epam.tests;

import com.epam.core.ConfigReader;
import com.epam.core.DriverFactory;
import com.epam.enums.ConfigKey;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver(ConfigReader.get(ConfigKey.BROWSER), ConfigReader.getBoolean(ConfigKey.HEADLESS));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}

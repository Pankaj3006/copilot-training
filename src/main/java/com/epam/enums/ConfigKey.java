package com.epam.enums;

public enum ConfigKey {
    BROWSER("browser"),
    HEADLESS("headless"),
    BASE_URL("base.url"),
    ORANGEHRM_URL("orangehrm.url"),
    ORANGEHRM_USERNAME("orangehrm.username"),
    ORANGEHRM_PASSWORD("orangehrm.password"),
    SAUCEDEMO_URL("saucedemo.url"),
    SAUCEDEMO_USERNAME("saucedemo.username"),
    SAUCEDEMO_PASSWORD("saucedemo.password"),
    SAUCEDEMO_CHECKOUT_FIRSTNAME("saucedemo.checkout.firstname"),
    SAUCEDEMO_CHECKOUT_LASTNAME("saucedemo.checkout.lastname"),
    SAUCEDEMO_CHECKOUT_ZIPCODE("saucedemo.checkout.zipcode");

    private final String key;

    ConfigKey(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}

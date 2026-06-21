package com.epam.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtil() {
    }

    public static String capture(WebDriver driver, String testName) {
        try {
            Path directory = Path.of("reports", "screenshots");
            Files.createDirectories(directory);

            String fileName = testName + "_" + LocalDateTime.now().format(TIMESTAMP_FORMATTER) + ".png";
            Path destination = directory.resolve(fileName);

            Files.copy(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );
            return destination.toString();
        } catch (IOException exception) {
            return null;
        }
    }
}

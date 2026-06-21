package com.epam.pages;

import com.epam.model.BusResult;
import com.epam.pages.locators.KsrtcSearchResultsPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultsPage extends BasePage {

    private static final Pattern SEAT_PATTERN = Pattern.compile("(\\d+)\\s+Seats?", Pattern.CASE_INSENSITIVE);

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage waitForResults() {
        waitForUrlContains("/search");
        waitForAllVisible(KsrtcSearchResultsPageLocators.RESULT_CARDS);
        return this;
    }

    public int getBusCount() {
        return driver.findElements(KsrtcSearchResultsPageLocators.RESULT_CARDS).size();
    }

    public List<BusResult> getBusResults() {
        List<WebElement> cards = driver.findElements(KsrtcSearchResultsPageLocators.RESULT_CARDS);
        List<BusResult> busResults = new ArrayList<>();

        for (WebElement card : cards) {
            List<String> lines = card.getText().lines()
                    .map(String::trim)
                    .filter(line -> !line.isBlank())
                    .toList();

            String serviceCode = lines.isEmpty() ? "UNKNOWN" : lines.get(0);
            String serviceName = lines.size() > 1 ? lines.get(1) : "UNKNOWN";
            int availableSeats = extractSeatCount(card.getText());

            busResults.add(new BusResult(serviceCode, serviceName, availableSeats));
        }

        return busResults;
    }

    public int getTotalAvailableSeats() {
        return getBusResults().stream()
                .mapToInt(BusResult::availableSeats)
                .sum();
    }

    public long getBusesWithAvailableSeatsCount() {
        return getBusResults().stream()
                .filter(result -> result.availableSeats() > 0)
                .count();
    }

    private int extractSeatCount(String cardText) {
        Matcher matcher = SEAT_PATTERN.matcher(cardText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }
}

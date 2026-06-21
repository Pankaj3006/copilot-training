package com.epam.tests;

import com.aventstack.extentreports.ExtentTest;
import com.epam.core.ConfigReader;
import com.epam.enums.ConfigKey;
import com.epam.listeners.ExtentTestListener;
import com.epam.model.BusResult;
import com.epam.pages.KsrtcHomePage;
import com.epam.pages.SearchResultsPage;
import com.epam.utils.DatabaseUtil;
import com.epam.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

@Listeners(ExtentTestListener.class)
public class KsrtcSearchTest extends BaseTest {

    @DataProvider(name = "locationDropdowns")
    public Object[][] getLocationDropdowns() {
        List<String> locations = DatabaseUtil.fetchLocationNames(2);
        return new Object[][] {
            { locations.get(0), locations.get(1) }
        };
    }

    @Test(description = "Search route options for locations fetched from database and count seats", dataProvider = "locationDropdowns")
    public void shouldCountBusesAndAvailableSeatsForTomorrow(String fromLocation, String toLocation) {
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        SearchResultsPage resultsPage = new KsrtcHomePage(getDriver())
            .open(ConfigReader.get(ConfigKey.BASE_URL))
                .selectLeavingFrom(fromLocation)
                .selectGoingTo(toLocation)
                .selectDepartureDate(tomorrow)
                .searchBuses();

        int busCount = resultsPage.getBusCount();
        int totalAvailableSeats = resultsPage.getTotalAvailableSeats();
        long busesWithSeats = resultsPage.getBusesWithAvailableSeatsCount();
        List<BusResult> busResults = resultsPage.getBusResults();

        ExtentTest test = ExtentTestListener.getTest();
        test.info("Route searched: " + fromLocation + " to " + toLocation);
        test.info("Journey date: " + tomorrow);
        test.info("Total buses listed: " + busCount);
        test.info("Buses with available seats: " + busesWithSeats);
        test.info("Total available seats: " + totalAvailableSeats);

        for (BusResult busResult : busResults) {
            test.info(String.format("%s | %s | Seats: %d",
                    busResult.serviceCode(),
                    busResult.serviceName(),
                    busResult.availableSeats()));
        }

        ExcelUtil.writeSearchResults(busCount, totalAvailableSeats, fromLocation, toLocation);
        test.info("Results written to: " + System.getProperty("user.home") + "\\Downloads\\output.xlsx");

        Assert.assertTrue(busCount > 0, "Expected at least one bus in the search results.");
        Assert.assertTrue(totalAvailableSeats >= 0, "Expected seat count to be non-negative.");
    }
}

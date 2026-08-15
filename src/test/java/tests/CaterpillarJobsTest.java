package tests;

import base.TestBase;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CaterpillarJobsPage;

@Owner("Aikerim Mukhtarbekova")
public class CaterpillarJobsTest extends TestBase {

    private final CaterpillarJobsPage jobsPage = new CaterpillarJobsPage();

    @Test
    @DisplayName("Job results are displayed")
    void pageOpensAndShowsJobsTest() {
        jobsPage.open()
                .verifyJobsAreDisplayed();
    }

    @Test
    @DisplayName("Job results for title Engineer are displayed")
    void searchReturnsResultsTest() {
        jobsPage.open()
                .closeCookieBanner()
                .search("Engineer")
                .verifyJobsAreDisplayed();
    }

    @Test
    @DisplayName("First job title is not empty")
    void firstJobTitleIsNotEmptyTest() {
        jobsPage.open()
                .verifyFirstJobTitleIsNotEmpty();
    }

    @Test
    @DisplayName("Job cards count is greater than 10")
    void jobCountIsPositiveNumberTest() {
        jobsPage.open()
                .verifyCardsCountIsGreaterThan(10);
    }
}
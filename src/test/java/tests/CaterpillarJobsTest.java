package tests;

import base.TestBase;
import org.junit.jupiter.api.Test;
import pages.CaterpillarJobsPage;

public class CaterpillarJobsTest extends TestBase {

    private final CaterpillarJobsPage jobsPage = new CaterpillarJobsPage();

    @Test
    void pageOpensAndShowsJobs() {
        jobsPage.open()
                .verifyJobsAreDisplayed();
    }

    @Test
    void searchReturnsResults() {
        jobsPage.open()
                .closeCookieBanner()
                .search("Engineer")
                .verifyJobsAreDisplayed();
    }

    @Test
    void pageShowsTwentyCards() {
        jobsPage.open()
                .verifyCardsCount(20);
    }

    @Test
    void firstJobTitleIsNotEmpty() {
        jobsPage.open()
                .verifyFirstJobTitleIsNotEmpty();
    }

    @Test
    void jobCountIsPositiveNumber() {
        jobsPage.open()
                .verifyCardsCountIsGreaterThan(10);
    }
}
package tests;

import base.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.CaterpillarJobsPage;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Epic("Caterpillar Careers")
@Feature("Job Search")
@Owner("Aikerim Mukhtarbekova")
public class JobSearchTests extends TestBase {

    private final CaterpillarJobsPage jobsPage = new CaterpillarJobsPage();

    @Test
    @Story("Jobs availability")
    @Severity(CRITICAL)
    @DisplayName("Job results are displayed")
    void jobResultsAreDisplayedTest() {
        jobsPage
                .openPage()
                .verifyJobsAreDisplayed();
    }

    @Test
    @Story("Job search")
    @Severity(CRITICAL)
    @DisplayName("Search jobs by Engineer keyword")
    void searchJobsByEngineerKeywordTest() {
        jobsPage
                .openPage()
                .closeCookieBanner()
                .searchForJob("Engineer")
                .verifyJobsAreDisplayed();
    }

    @ParameterizedTest(name = "Search jobs by keyword: {0}")
    @ValueSource(strings = {
            "Engineer",
            "Software",
            "Manager"
    })
    @Story("Job search")
    @Severity(NORMAL)
    @DisplayName("Search jobs using different keywords")
    void searchJobsByDifferentKeywordsTest(String keyword) {
        jobsPage
                .openPage()
                .closeCookieBanner()
                .searchForJob(keyword)
                .verifyJobsAreDisplayed();
    }

    @Test
    @Story("Job search results")
    @Severity(NORMAL)
    @DisplayName("First job title is not empty")
    void firstJobTitleIsNotEmptyTest() {
        jobsPage
                .openPage()
                .verifyFirstJobTitleIsNotEmpty();
    }
}
package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CaterpillarJobsPage {

    private static final String JOBS_URL =
            "/jobs/?search=&team=Technology%2C+Digital+and+Data&team=Engineering#results";

    private final SelenideElement searchInput = $("#l-search");
    private final SelenideElement searchButton = $("#js-main-job-search");
    private final SelenideElement resultsContainer = $("#js-job-search-results");

    private final SelenideElement cookieBanner = $("#onetrust-banner-sdk");
    private final SelenideElement acceptCookiesButton = $("#onetrust-accept-btn-handler");

    private final ElementsCollection jobCards = $$(".card-job");

    @Step("Open Caterpillar jobs page")
    public CaterpillarJobsPage openPage() {
        open(JOBS_URL);

        resultsContainer.shouldBe(visible);

        return this;
    }

    @Step("Close cookie banner if it is displayed")
    public CaterpillarJobsPage closeCookieBanner() {

        if (acceptCookiesButton.is(visible, Duration.ofSeconds(5))) {
            acceptCookiesButton.click();

            cookieBanner.shouldBe(
                    disappear,
                    Duration.ofSeconds(5)
            );
        }

        return this;
    }

    @Step("Search jobs by query: {query}")
    public CaterpillarJobsPage searchForJob(String query) {

        searchInput
                .shouldBe(visible)
                .setValue(query);

        searchButton
                .shouldBe(visible)
                .shouldBe(enabled)
                .click();

        resultsContainer.shouldBe(visible);

        return this;
    }

    @Step("Verify that job results are displayed")
    public CaterpillarJobsPage verifyJobsAreDisplayed() {
        jobCards.shouldHave(sizeGreaterThan(0));

        return this;
    }

    @Step("Verify that the first job title is not empty")
    public CaterpillarJobsPage verifyFirstJobTitleIsNotEmpty() {
        jobCards.first()
                .$(".js-view-job")
                .shouldBe(visible)
                .shouldNotBe(empty);

        return this;
    }
}
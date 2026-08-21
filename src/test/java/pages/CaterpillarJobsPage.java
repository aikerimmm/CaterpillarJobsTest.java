package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class CaterpillarJobsPage {

    private static final String JOBS_URL =
            "/jobs/?search=&team=Technology%2C+Digital+and+Data&team=Engineering#results";

    private final SelenideElement searchInput = $("#l-search");
    private final SelenideElement searchButton = $("#js-main-job-search");
    private final SelenideElement resultsContainer = $("#js-job-search-results");

    private final ElementsCollection jobCards = $$(".card-job");

    @Step("Open Caterpillar jobs page")
    public CaterpillarJobsPage openPage() {
        open(JOBS_URL);
        resultsContainer.shouldBe(visible);
        return this;
    }

    @Step("Close cookie banner if it is displayed")
    public CaterpillarJobsPage closeCookieBanner() {
        SelenideElement cookieButton =
                $("[id*='cookie'] button, .js-accept-cookies, #onetrust-accept-btn-handler");

        if (cookieButton.exists()) {
            cookieButton.click();
        }

        return this;
    }

    @Step("Search jobs by query: {query}")
    public CaterpillarJobsPage searchForJob(String query) {
        searchInput
                .shouldBe(visible)
                .setValue(query);

        searchButton
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

    @Step("Verify that job cards count is greater than {minSize}")
    public CaterpillarJobsPage verifyCardsCountIsGreaterThan(int minSize) {
        jobCards.shouldHave(sizeGreaterThan(minSize));
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
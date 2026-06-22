package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class CaterpillarJobsPage {

    private final SelenideElement searchInput = $("#l-search");
    private final SelenideElement searchButton = $("#js-main-job-search");
    private final SelenideElement resultsContainer = $("#js-job-search-results");
    private final ElementsCollection jobCards = $$(".card-job");

    public CaterpillarJobsPage open() {
        com.codeborne.selenide.Selenide.open("https://careers.caterpillar.com/en/jobs/?search=&team=Technology%2C+Digital+and+Data&team=Engineering#results");
        return this;
    }

    public CaterpillarJobsPage closeCookieBanner() {
        SelenideElement cookieBtn = $("[id*='cookie'] button, .js-accept-cookies, #onetrust-accept-btn-handler");
        if (cookieBtn.exists()) {
            cookieBtn.click();
        }
        return this;
    }

    public CaterpillarJobsPage search(String query) {
        searchInput.setValue(query);
        searchButton.click();
        return this;
    }

    public int getJobCount() {
        return Integer.parseInt(resultsContainer.attr("data-results"));
    }

    public int getVisibleCardCount() {
        return jobCards.size();
    }

    public String getFirstJobTitle() {
        return jobCards.first().$(".js-view-job").getText();
    }
}
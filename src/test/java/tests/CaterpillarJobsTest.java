package tests;

import org.junit.jupiter.api.Test;
import pages.CaterpillarJobsPage;
import static org.junit.jupiter.api.Assertions.*;

public class CaterpillarJobsTest {

    private final CaterpillarJobsPage jobsPage = new CaterpillarJobsPage();

    @Test
    void pageOpensAndShowsJobs() {
        jobsPage.open();
        assertTrue(jobsPage.getJobCount() > 0);
    }

    @Test
    void searchReturnsResults() {
        jobsPage.open();
        jobsPage.closeCookieBanner();
        jobsPage.search("Engineer");
        assertTrue(jobsPage.getJobCount() > 0);
    }

    @Test
    void pageShowsTwentyCards() {
        jobsPage.open();
        assertEquals(20, jobsPage.getVisibleCardCount());
    }

    @Test
    void firstJobTitleIsNotEmpty() {
        jobsPage.open();
        assertFalse(jobsPage.getFirstJobTitle().isEmpty());
    }

    @Test
    void jobCountIsPositiveNumber() {
        jobsPage.open();
        int count = jobsPage.getJobCount();
        assertTrue(count > 100);
    }
}
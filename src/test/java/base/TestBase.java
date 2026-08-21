package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import utils.Attach;

public class TestBase {

    @BeforeAll
    static void setUp() {

        Configuration.baseUrl = System.getProperty(
                "baseUrl",
                "https://careers.caterpillar.com/en"
        );

        Configuration.browser = System.getProperty(
                "browser",
                "chrome"
        );

        Configuration.browserVersion = System.getProperty(
                "browserVersion",
                ""
        );

        Configuration.browserSize = System.getProperty(
                "browserSize",
                "1920x1080"
        );

        Configuration.remote = System.getProperty(
                "remoteUrl"
        );

        Configuration.pageLoadStrategy = "eager";

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(false)
                        .savePageSource(false)
        );
    }

    @AfterEach
    void addAttachments() {

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();

        Selenide.closeWebDriver();
    }
}
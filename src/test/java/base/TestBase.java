package base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterEach
    void addAttachments() {
        addScreenshot();
        addPageSource();
    }

    private void addScreenshot() {
        byte[] screenshot = Selenide.screenshot(OutputType.BYTES);

        if (screenshot != null) {
            Allure.addAttachment(
                    "Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );
        }
    }

    private void addPageSource() {
        Allure.addAttachment(
                "Page Source",
                "text/html",
                Selenide.webdriver().driver().source(),
                ".html"
        );
    }
}
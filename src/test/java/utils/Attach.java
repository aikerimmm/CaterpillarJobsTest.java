package utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.logging.LogType;

import java.io.ByteArrayInputStream;

public class Attach {

    private Attach() {
    }

    public static void screenshotAs(String name) {
        byte[] screenshot = Selenide.screenshot(OutputType.BYTES);

        if (screenshot != null) {
            Allure.addAttachment(
                    name,
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );
        }
    }

    public static void pageSource() {
        Allure.addAttachment(
                "Page Source",
                "text/html",
                Selenide.webdriver().driver().source(),
                ".html"
        );
    }

    public static void browserConsoleLogs() {
        String logs = String.join(
                "\n",
                Selenide.webdriver()
                        .driver()
                        .getWebDriver()
                        .manage()
                        .logs()
                        .get(LogType.BROWSER)
                        .getAll()
                        .stream()
                        .map(Object::toString)
                        .toList()
        );

        Allure.addAttachment(
                "Browser console logs",
                "text/plain",
                logs
        );
    }
}
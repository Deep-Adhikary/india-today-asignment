package assignment.indiatoday.base.factories;

import java.nio.file.FileSystems;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverCreator extends WebDriverCreator {
    protected void setWebDriverPath() {
        String chromedriverpath = FileSystems
                .getDefault()
                .getPath("./src/test/java/assignment/indiatoday/drivers/chromedriver.exe").normalize()
                .toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", chromedriverpath);
    }

    public WebDriverCreator initialize() {
        setWebDriverPath();
        driver = new ChromeDriver();
        return this;
    }
}

package assignment.indiatoday.base.factories;

import java.nio.file.FileSystems;

import org.openqa.selenium.firefox.FirefoxDriver;

public class GeckoDriverCreator extends WebDriverCreator {

    @Override
    protected void setWebDriverPath() {
        String geckodriverpath = FileSystems
                .getDefault()
                .getPath("./src/test/java/assignment/indiatoday/drivers/geckodriver.exe").normalize()
                .toAbsolutePath().toString();
        System.setProperty("webdriver.gecko.driver", geckodriverpath);

    }

    @Override
    public WebDriverCreator initialize() {
        setWebDriverPath();
        driver = new FirefoxDriver();
        return this;
    }

}

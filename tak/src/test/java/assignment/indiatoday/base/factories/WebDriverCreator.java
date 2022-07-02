package assignment.indiatoday.base.factories;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import assignment.indiatoday.base.utils.PropertyReader;

public abstract class WebDriverCreator {
    protected WebDriver driver;
    protected PropertyReader reader = PropertyReader.getInstance();

    protected abstract void setWebDriverPath();

    public abstract WebDriverCreator initialize();

    public WebDriver get() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Long.parseLong(reader.get("implicitwait"))));
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(
                        Long.parseLong(reader.get("pageloadtimeout"))));
        driver.manage().timeouts().scriptTimeout(
                Duration.ofSeconds(
                        Long.parseLong(reader.get("scripttimeout"))));
        return driver;
    }
}

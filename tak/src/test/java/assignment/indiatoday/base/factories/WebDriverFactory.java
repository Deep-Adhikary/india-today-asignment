package assignment.indiatoday.base.factories;

import org.openqa.selenium.WebDriver;

import assignment.indiatoday.base.utils.Browser;

public class WebDriverFactory {
    public static WebDriver getDriver(Browser browser) throws Exception {
        WebDriverCreator creator = null;
        if (browser == Browser.CHROME) {
            creator = new ChromeDriverCreator();
        }
        if (browser == Browser.FIREFOX) {
            creator = new GeckoDriverCreator();
        }
        if (creator == null) {
            throw new Exception("Unsupported Browser" + browser.name());
        }
        return creator.initialize().get();
    }

}

package assignment.indiatoday;

import org.testng.annotations.*;

import assignment.indiatoday.base.factories.WebDriverFactory;
import assignment.indiatoday.base.utils.Browser;
import assignment.indiatoday.base.utils.PropertyReader;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SportsTakTest {
    /**
     * Rigorous Test :-)
     */
    private WebDriver driver;
    private PropertyReader reader;

    @BeforeClass
    public void setUp() throws Exception {
        driver = WebDriverFactory.getDriver(Browser.CHROME);
        reader = PropertyReader.getInstance();
    }

    @Test
    public void launchAppandSelectLanguage() {
        driver.navigate().to(reader.get("appurl"));

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

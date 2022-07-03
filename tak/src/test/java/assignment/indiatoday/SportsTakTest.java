package assignment.indiatoday;

import org.testng.annotations.*;

import assignment.indiatoday.base.factories.WebDriverFactory;
import assignment.indiatoday.base.pages.Home;
import assignment.indiatoday.base.utils.Browser;
import assignment.indiatoday.base.utils.PropertyReader;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SportsTakTest {
    /**
     * Rigorous Test :-)
     */
    private WebDriver driver;

    @SuppressWarnings("unused")
    private PropertyReader reader;

    private Home homepage;

    @BeforeClass
    public void setUp() throws Exception {
        driver = WebDriverFactory.getDriver(Browser.FIREFOX);
        reader = PropertyReader.getInstance();
        homepage = new Home(driver);
    }

    @Test
    public void launchAppandChangeLanguage() {
        homepage.navigate();
        homepage.getLeftMenu().open();
        homepage.getLeftMenu().changeLanguage("hindi");
        homepage.verifyLanguageIsSetTo("hindi");
        homepage.getLeftMenu().open();
        homepage.getLeftMenu().refreshePageObjects();
        homepage.getLeftMenu().changeLanguage("english");

    }

    @Test
    void verifyPageTheme() {
        homepage.navigate();
        homepage.getLeftMenu().open();
        homepage.getLeftMenu().changeTheme();
        homepage.verifyPageThemeIs("white");
        homepage.getLeftMenu().refreshePageObjects();
        homepage.getLeftMenu().open();
        homepage.getLeftMenu().changeTheme();
        homepage.verifyPageThemeIs("black");

    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}

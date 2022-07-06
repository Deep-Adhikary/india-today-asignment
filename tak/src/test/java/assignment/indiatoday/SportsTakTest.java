package assignment.indiatoday;

import org.testng.annotations.*;

import assignment.indiatoday.base.BaseTest;
import assignment.indiatoday.base.factories.WebDriverFactory;
import assignment.indiatoday.base.pages.Home;
import assignment.indiatoday.base.pages.WebStories;
import assignment.indiatoday.base.utils.Browser;
import assignment.indiatoday.base.utils.PropertyReader;

public class SportsTakTest extends BaseTest {
    /**
     * Rigorous Test :-)
     */
    private Home homepage;
    private WebStories webStoriesPage;

    @BeforeClass
    public void setUp() throws Exception {
        driver = WebDriverFactory.getDriver(Browser.FIREFOX);
        reader = PropertyReader.getInstance();
        homepage = new Home(driver);
        webStoriesPage = new WebStories(driver);
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
    void verifyAllHomePageLinksAreWorking() {
        homepage.navigate();
        homepage.verifyAllLinks();
    }

    @Test
    public void verifyPageTheme() {
        homepage.navigate();
        homepage.getLeftMenu().open();
        homepage.getLeftMenu().changeTheme();
        homepage.verifyPageThemeIs("white");
        homepage.getLeftMenu().refreshePageObjects();
        homepage.getLeftMenu().open();
        homepage.getLeftMenu().changeTheme();
        homepage.verifyPageThemeIs("black");

    }

    @Test
    public void VerifyWebStories() {
        homepage.navigate();
        homepage.getTopNavigation().navigateTo("webstory");
        webStoriesPage.selectFirstWebStory();
        webStoriesPage.switchToActiveStory();
        webStoriesPage.changeStoryAndCaptureText();
    }
    @AfterClass
    public void tearDown() {
        // driver.close();
        driver.quit();
    }

}

package assignment.indiatoday.base.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import assignment.indiatoday.base.BasePage;

public class Home extends BasePage {
    @FindBy(tagName = "html")
    WebElement rootHtml;

    public Home(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(reader.get("appurl"));
        refreshePageObjects();
        getLeftMenu().refreshePageObjects();
    }

    public String getCurrentTheme(){
        return rootHtml.getAttribute("class").split(" ")[1];
    }
    public void verifyPageThemeIs(String theme){
        Assert.assertEquals(getCurrentTheme().toLowerCase(), theme.toLowerCase());
    }

    public void verifyLanguageIsSetTo(String language) {
        if (language.equalsIgnoreCase("hindi")) {
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("hindi"));
            return;
        }
        if (language.equalsIgnoreCase("english")) {
            Assert.assertFalse(driver.getCurrentUrl().toLowerCase().contains("hindi"));
            return;
        }
    }
}

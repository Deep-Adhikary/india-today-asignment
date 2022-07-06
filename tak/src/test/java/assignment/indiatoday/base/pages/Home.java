package assignment.indiatoday.base.pages;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.TimeoutException;

import assignment.indiatoday.base.BasePage;

public class Home extends BasePage {
    @FindBy(tagName = "html")
    WebElement rootHtml;

    @FindAll(@FindBy(xpath = "//button[contains(@class,'mediumNewsCard-action')]"))
    private List<WebElement> allCards;

    @FindBy(id = "news-frame")
    private WebElement buzzIframe;
    String allcardLocator = "//button[contains(@class,'mediumNewsCard-action')]//p[contains(@class,'mediumNewsCard-title')]";

    By locAppCocpit=By.cssSelector("div.app__cockpit");
    public Home(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.navigate().to(reader.get("appurl"));
        refreshePageObjects();
        getLeftMenu().refreshePageObjects();
        custReporter.logInfo("Successfully navigated to home page");

    }

    public String getCurrentTheme() {
        return rootHtml.getAttribute("class").split(" ")[1];
    }

    public void verifyPageThemeIs(String theme) {
        Assert.assertEquals(getCurrentTheme().toLowerCase(), theme.toLowerCase());
        custReporter.logInfo("Theme Verification successfule for :'"+ theme+ "'");

    }

    public void verifyLanguageIsSetTo(String language) {
        if (language.equalsIgnoreCase("hindi")) {
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("hindi"));
            custReporter.logInfo("Language is successfully set to:'"+ language+ "'");

            return;
        }
        if (language.equalsIgnoreCase("english")) {
            Assert.assertFalse(driver.getCurrentUrl().toLowerCase().contains("hindi"));
            custReporter.logInfo("Language is successfully set to:'"+ language+ "'");

            return;
        }
    }

    public void verifyAllLinks() {
        SoftAssert softAssert = new SoftAssert();
        int allcardCount = driver.findElements(By.xpath(allcardLocator)).size();

        for (int i = 1; i <= allcardCount; i++) {
            WebElement cardButton = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("(" + allcardLocator + ")" + "[" + i + "]")));
            String title = cardButton.getText();
            custReporter.logInfo("Verifying Link for title:'"+ title+ "'");
            //Had to use javascript click as normal click thowing error element not interactable
            customcapabilities.scrollIntoViewUsingJavaScript(cardButton);
            customcapabilities.clickUsingJavaScript(cardButton);
            boolean status=false;
            try {
                status = wait.until(ExpectedConditions.stalenessOf(cardButton));
                softAssert.assertTrue(status);
                sleep(3000);
                // getTopNavigation().refreshePageObjects();
                boolean isLinksBelongstoVideos = getTopNavigation()
                        .getActive()
                        .equalsIgnoreCase("videos");
                boolean isLinksBelongstoBuzz = getTopNavigation()
                        .getActive()
                        .equalsIgnoreCase("buzz");


                if (isLinksBelongstoVideos) {

                    wait.until(ExpectedConditions.textMatches(locAppCocpit,
                            Pattern.compile(".*" + title + ".*", Pattern.MULTILINE)));                    

                }
                if (isLinksBelongstoBuzz) {
                    sleep(5000);
                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(buzzIframe));
                    wait.until(ExpectedConditions.textMatches(By.cssSelector("body"),
                            Pattern.compile(".*" + title + ".*", Pattern.MULTILINE)));
                    driver.switchTo().defaultContent();


                }
                custReporter.logInfo("Link verifiction successfull for title:'"+ title+ "'");

                this.getTopNavigation().navigateTo("Home");
                sleep(2000);

            } catch (TimeoutException e) {
                System.out.println("Link not working for '" + title + "'");
                custReporter.logError("Link verifiction failed for title:'"+ title+ "'");

                softAssert.assertTrue(status);
            }

        }
        softAssert.assertAll();
    }
}

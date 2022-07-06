package assignment.indiatoday.base.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import assignment.indiatoday.base.BasePage;

public class WebStories extends BasePage {

    @FindBy(css = "div.contentstory iframe")
    private WebElement webStoryIframe;

    @FindBy(css = "div.largestory.card") // div.largestory.card div.cardin
    private WebElement firstWebStoryCard;

    @FindBy(tagName = "body")
    private WebElement frameBody;

    @FindBy(css = "#amp-story-list>div")
    private WebElement storyShadowRoot;

    @FindBy(css = "div.next-container>button")
    private WebElement nextStory;

    @FindBy(css = "iframe.story-player-iframe[i-amphtml-iframe-position='0']")
    private WebElement activeStoryFrame;

    @FindAll(@FindBy(css = "amp-story-page"))
    private List<WebElement> allStoryFrames;

    @FindBy(css = "amp-story-page[active] div.letterbox")
    private WebElement activeStoryTextContainer;

    private String storyIframe = "iframe.story-player-iframe[i-amphtml-iframe-position='0']";

    public WebStories(WebDriver driver) {
        super(driver);
    }

    public void selectFirstWebStory() {
        driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOf(webStoryIframe)));
        sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(firstWebStoryCard)).click();
        wait.until(ExpectedConditions.attributeContains(frameBody, "class", "home-page-content"));
        Reporter.log("WebStory successfully selected");
        driver.switchTo().defaultContent();
    }

    public void switchToActiveStory() {
        driver.switchTo().frame(
                wait.until(ExpectedConditions.visibilityOf(webStoryIframe)));
        sleep(5000);

        WebElement storyShadow = customcapabilities.expandShadowRootElement(storyShadowRoot);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                storyShadow.findElement(
                        By.cssSelector(storyIframe))));
        sleep(2000);
    }

    public void changeStoryAndCaptureText() {
        int count = allStoryFrames.size();
        int page = 1;
        do {
            String storyText = wait.until(ExpectedConditions.visibilityOf(activeStoryTextContainer)).getText();
            System.out.println(page + ":" + storyText);
            Reporter.log("WebStory Text Captured:"+ storyText );

            customcapabilities.clickUsingJavaScript(wait.until(ExpectedConditions.elementToBeClickable(nextStory)));
            sleep(2000);
            page++;
        } while (page <= count);
    }
}

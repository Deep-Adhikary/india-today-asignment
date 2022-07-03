package assignment.indiatoday.base.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.Assert;

import assignment.indiatoday.base.BasePage;

public class LeftMenu extends BasePage {
    @FindBy(css = "button[aria-label='menu']")
    private WebElement leftSandwitchMenu;

    @FindBy(css = "div.sidebar__container ")
    private WebElement sideBarContainer;

    @CacheLookup
    @FindAll(@FindBy(css = "div.sidebar__container a"))
    private List<WebElement> sidebarLinks;

    @FindBy(xpath = "//button[.='Theme']")
    private WebElement theamButton;

    @FindBy(css = "p.close-icon")
    private WebElement closeButton;

    public LeftMenu(WebDriver driver) {
        super(driver);

    }

    public void open() {
        wait.until(ExpectedConditions.elementToBeClickable(leftSandwitchMenu)).click();
        wait.until(ExpectedConditions.attributeContains(sideBarContainer, "class", "active"));
        Assert.assertTrue(sidebarLinks.size() > 0);

    }

    public void close() {
        wait.until(ExpectedConditions.visibilityOf(closeButton)).click();
    }

    public void changeLanguage(String language) {
        if (language.equalsIgnoreCase("english")) {
            wait.until(ExpectedConditions.visibilityOf(getLinkByText("भाषा बदलें"))).click();
            return;
        }
        if (language.equalsIgnoreCase("hindi")) {
            wait.until(ExpectedConditions.visibilityOf(getLinkByText("change language"))).click();
            return;
        }
    }

    public void changeTheme() {
        wait.until(ExpectedConditions.visibilityOf(theamButton)).click();
    }

    private WebElement getLinkByText(String text) {
        return sidebarLinks
                .stream()
                .filter(
                        element -> element.getText().equalsIgnoreCase(text))
                .findFirst()
                .get();
    }

}

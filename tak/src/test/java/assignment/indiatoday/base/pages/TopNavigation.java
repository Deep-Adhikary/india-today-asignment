package assignment.indiatoday.base.pages;

import java.util.List;

import javax.lang.model.element.Element;
import javax.swing.text.html.CSS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import assignment.indiatoday.base.BasePage;

public class TopNavigation extends BasePage {
    @FindAll(@FindBy(css = "div.feature__container a"))
    private List<WebElement> topNavigationLinks;

    public TopNavigation(WebDriver driver) {
        super(driver);
    }

    public void navigateTo(String linkText) {
        wait.until(ExpectedConditions.visibilityOf(
                topNavigationLinks.stream().filter(
                        element -> element.getText().equalsIgnoreCase(linkText)).findFirst().get()))
                .click();
    }
}

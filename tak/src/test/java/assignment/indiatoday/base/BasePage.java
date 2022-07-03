package assignment.indiatoday.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import assignment.indiatoday.base.pages.LeftMenu;
import assignment.indiatoday.base.utils.PropertyReader;

import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected PropertyReader reader;
    private LeftMenu leftMenu;
    public BasePage(WebDriver driver) {
        reader = PropertyReader.getInstance();
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        PageFactory.initElements(this.driver, this);
    }
    public void refreshePageObjects(){
        PageFactory.initElements(this.driver, this);
    }
    public LeftMenu getLeftMenu(){
        if (leftMenu!=null){
            return leftMenu;
        }
        leftMenu=new LeftMenu(driver);
        return leftMenu;
    }
    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        this.driver.navigate().refresh();
    }
}

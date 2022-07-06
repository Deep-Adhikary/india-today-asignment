package assignment.indiatoday.base.utils;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomCapabilities {
    private WebDriver driver;

    public CustomCapabilities(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement expandShadowRootElement(WebElement element) {
        @SuppressWarnings("unchecked")
        List<WebElement> eles = (List<WebElement>) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot.children", element);
        return eles.get(0);
    }

    public void scrollIntoViewUsingJavaScript(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickUsingJavaScript(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }
}

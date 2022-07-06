package assignment.indiatoday.base;


import assignment.indiatoday.base.utils.CustomReporter;
import assignment.indiatoday.base.utils.PropertyReader;

import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    protected PropertyReader reader;

    

    

    public CustomReporter getCustomReporter() {
        return new CustomReporter(this.driver);
    }
}

package assignment.indiatoday.base.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CustomReporter {
    private WebDriver driver;
    private TakesScreenshot screenShot;
    private static String resultFolderPath = System.getProperty("user.dir") + "/results";
    private static String screenshotPath = resultFolderPath + "/screenshots";

    private enum Colors {
        INFO("#295d04"), ERROR("#db3d20");

        public final String value;

        private Colors(String value) {
            this.value = value;
        }
    }

    public static void cleanResultFolder() {
        FileUtils.deleteQuietly(new File(resultFolderPath));
        new File(resultFolderPath).mkdir();
        new File(screenshotPath).mkdir();
    }

    public CustomReporter(WebDriver driver) {
        this.driver = driver;
        screenShot = ((TakesScreenshot) this.driver);
    }

    public void logInfo(String msg) {
        Reporter.log(logBuilder(msg, Colors.INFO));
    }

    public void logError(String msg) {
        Reporter.log(logBuilder(msg, Colors.ERROR));
    }

    private String logBuilder(String msg, Colors color) {
        StringBuilder builder = new StringBuilder();
        builder.append("<div style='color: " + color.value + ";'>");
        builder.append("<strong>");
        builder.append(msg);
        builder.append(". ");
        builder.append("</strong>");

        builder.append("<a href=" + capturePageScreenshot() + ">");
        builder.append("Screenshot");
        builder.append("</a>");
        builder.append("</div>");
        return builder.toString();
    }

    private String capturePageScreenshot() {
        String screenShotName = screenshotPath + "/" + getScreenshotName() + ".png";
        File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenShotName);
        try {
            FileUtils.moveFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenShotName;
    }

    private String getScreenshotName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }
}

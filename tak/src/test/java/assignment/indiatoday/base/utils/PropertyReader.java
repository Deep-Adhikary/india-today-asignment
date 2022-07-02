package assignment.indiatoday.base.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private Properties configs;
    private static PropertyReader INSTANCE;

    private PropertyReader() {

        try {
            configs = new Properties();
            FileReader propertyfile = new FileReader(
                    System.getProperty("user.dir")
                            + "/src/test/java/assignment/indiatoday/configurations/properties/configurations.properties");
            configs.load(propertyfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyReader getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            INSTANCE = new PropertyReader();
            return INSTANCE;
        }
    }

    public String get(String key) {
        return configs.getProperty(key);
    }
}

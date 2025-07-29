package com.STL.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;

    public static String getProperty(String key) {
        if (prop == null) {
            prop = new Properties();
            try {
                FileInputStream file = new FileInputStream("src/test/resources/config.properties"); // ✅ Path points to test/resources
                prop.load(file);
                System.out.println("Loaded headless mode: " + prop.getProperty("headless"));  // Optional Jenkins debug log
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop.getProperty(key);
    }

    //  Headless mode check
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }
}

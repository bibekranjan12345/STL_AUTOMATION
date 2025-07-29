package com.STL.Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	static Properties prop;

	public static String getProperty(String key) {
		if (prop == null) {
			prop = new Properties();
			try {
				// First try to load from classpath (works in Maven, Jenkins, etc.)
				InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
				if (input != null) {
					prop.load(input);
					System.out.println("Loaded config.properties via classpath ");
				} else {
					// Fallback to direct file path (useful in Eclipse or manual runs)
					FileInputStream file = new FileInputStream("src/test/resources/config.properties");
					prop.load(file);
					System.out.println("Loaded config.properties via fallback path ");
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("config.properties not found in classpath or fallback path");
			}
		}
		return prop.getProperty(key);
	}

	public static boolean isHeadless() {
		return Boolean.parseBoolean(getProperty("headless"));
	}
}

package com.STL.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader 
{
	private static Properties prop = new Properties();

	static
	{
		try 
		{
			FileInputStream fis = new FileInputStream("./src/main/java/com/STL/Config/Config.properties");
			prop.load(fis);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
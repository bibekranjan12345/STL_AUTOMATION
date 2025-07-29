package com.STL.Constants;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constants 
{
	public static final By welcomeBEO_Text=By.xpath("(//div[text()=' Welcome BEO '])[2]");  // Validation of LoginPage
	public static final By crccText=By.xpath("//div[text()='CRCC List']");		// Validation Of CRCC List Page


	public static String loginPageValidation(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		String loginValidation = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeBEO_Text)).getText();
		return loginValidation;
	}

	public static String crccListPageValidation(WebDriver driver)
	{
		String crccList=driver.findElement(crccText).getText();
		return crccList;
	}


}

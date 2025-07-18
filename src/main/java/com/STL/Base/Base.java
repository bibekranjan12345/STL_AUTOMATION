package com.STL.Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base 
{

	protected WebDriver driver;

	public Base(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	protected void type(WebElement element, String text) 
	{
		element.clear();
		element.sendKeys(text);
	}

	public void click(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected String getText(WebElement element) 
	{
		return element.getText();
	}


	public void waitForSpinnerToDisappear() 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		By spinnerLocator = By.cssSelector("div.ngx-spinner-overlay");

		int maxAttempts = 5; // Max retries in case spinner reappears

		for (int i = 0; i < maxAttempts; i++)
		{
			try {
				// Wait until spinner appears (optional - handles delay between requests)
				wait.until(ExpectedConditions.presenceOfElementLocated(spinnerLocator));
				// Wait until spinner disappears
				wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
				// Small pause to make sure animation doesn't flicker back
				Thread.sleep(300);
			} catch (Exception e) {
				break; // Spinner not found or already gone
			}
		}
	}
















	//	public void waitForSpinnerToDisappear() {
	//		try {
	//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	//			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.ngx-spinner-overlay")));
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			System.out.println("Spinner did not disappear in time.");
	//		}
	//	}































	//	protected boolean isElementObstructed(WebElement element) {
	//		JavascriptExecutor js = (JavascriptExecutor) driver;
	//		String script =	"var elem = arguments[0],                " +
	//						"    box = elem.getBoundingClientRect(), " +
	//						"    centerX = box.left + box.width/2,   " +
	//						"    centerY = box.top + box.height/2,   " +
	//						"    el = document.elementFromPoint(centerX, centerY); " +
	//						"for (; el; el = el.parentElement) {     " +
	//						"  if (el === elem) return false;       " +
	//						"}                                       " +
	//						"return true;";
	//
	//		return (Boolean) js.executeScript(script, element);
	//	}

}



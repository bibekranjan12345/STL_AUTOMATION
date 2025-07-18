package com.STL.Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Base.Base;

public class LoginPage extends Base {


    @FindBy(id = "userid")
    private WebElement userIdTextField;

    @FindBy(id = "Password")
    private WebElement passwordTextField;

    @FindBy(id = "captcha")
    private WebElement captchaTextField;

    @FindBy(xpath = "//button[.=' Submit ']")
    private WebElement submitButton;

    @FindBy(xpath = "(//a[.=' Login '])[2]")
    private WebElement loginButton;

    @FindBy(css = ".ngx-spinner-overlay")
    private WebElement spinnerOverlay;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUserId(String userId) {
        type(userIdTextField, userId);
    }

    public void enterPassword(String password) {
        type(passwordTextField, password);
    }

    public void enterCaptcha(String captcha) {
        type(captchaTextField, captcha);
    }

    public void clickLogin() {
        click(loginButton);
    }
    
    
    public void clickSubmit()
    {
    	try
    	{
    		Robot rb=new Robot();
    		rb.keyPress(KeyEvent.VK_ENTER);
    		rb.keyRelease(KeyEvent.VK_ENTER);
    	}
    	catch(AWTException e)
    	{
    		e.printStackTrace();
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//    public void clickSubmit() {
//        try 
//        {
//            wait.until(ExpectedConditions.invisibilityOf(spinnerOverlay));
//        } 
//        catch (Exception e) 
//        {
//            System.out.println("Spinner overlay not visible or already gone.");
//        }
//
//        try 
//        {
//            Thread.sleep(500); // Let animations finish
//        }
//        catch (InterruptedException e) 
//        {
//            Thread.currentThread().interrupt();
//        }
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView({block:'center'});", submitButton);
//
//        try 
//        {
//            while (isElementObstructed(submitButton)) 
//            {
//                System.out.println("Submit button still obstructed, waiting...");
//                Thread.sleep(500);
//            }
//            wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
//        } 
//        catch (Exception e) 
//        {
//            System.out.println("Normal click failed: " + e.getMessage());
//            System.out.println("Trying JS click for Submit button.");
//            js.executeScript("arguments[0].click();", submitButton);
//        }
//    }
}

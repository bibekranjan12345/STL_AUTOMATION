package com.STL.Feature;

import org.openqa.selenium.WebDriver;

import com.STL.Pages.LoginPage;
import com.STL.Utils.ConfigReader;
public class LoginFeature 
{
    private LoginPage loginPage;

    public LoginFeature(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public void loginToApplication() 
    {
        String userId=ConfigReader.getProperty("loginemail");
        String password=ConfigReader.getProperty("loginpassword");
        String captcha=ConfigReader.getProperty("captcha");
        
        loginPage.clickLogin();
        loginPage.enterUserId(userId);
        loginPage.enterPassword(password);
        loginPage.enterCaptcha(captcha);
        loginPage.clickSubmit();
    }

}

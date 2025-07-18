package com.STL.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Base.Base;
import com.STL.Utils.ConfigReader;

public class CreateCRCCPage extends Base
{

	public CreateCRCCPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//span[@class='ng-arrow-wrapper']/..")
    private WebElement schoolNameDropdown;

    @FindBy(xpath = "//span[text()='KHADALPOKHARI PROJECT U. P.S.-21090527201']")
    private WebElement optionOfSchoolName;

    @FindBy(xpath = "//input[@placeholder='CRCC Name']")
    private WebElement crccNameTextField;

    @FindBy(xpath = "//input[@placeholder='Mobile No']")
    private WebElement mobileNoTextField;

    @FindBy(xpath = "//input[@placeholder='Email ID']")
    private WebElement emailIdTextField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    private WebElement confirmPasswordTextField;

    @FindBy(xpath = "//select[@formcontrolname='statusId']")
    private WebElement statusDropdown;

    @FindBy(xpath = "//option[.='Active']")
    private WebElement optionOfStatus;

    @FindBy(xpath = "//button[.='Submit ']")
    private WebElement submitButton;
    
    public void enterSchoolNameDD()
    {
    	click(schoolNameDropdown);
    }
    
    public void enterSchoolOption()
    {
    	click(optionOfSchoolName);
    }
    
    public String enterCRCCName()
    {
    	String name=ConfigReader.getProperty("name");
    	type(crccNameTextField, name);
    	return name;
    }
    public String enterMobileNo()
    {
    	String mobileNo=ConfigReader.getProperty("mobileNo");
    	type(mobileNoTextField, mobileNo);
    	return mobileNo;
    }

    public String enterEmail()
    {
    	String email=ConfigReader.getProperty("email");
    	type(emailIdTextField, email);
    	return email;
    }
    
    public String enterPassword()
    {
    	String password=ConfigReader.getProperty("password");
    	type(passwordTextField, password);
    	return password;
    }
    
    public String enterConfirmPassword()
    {
    	String conPassword=ConfigReader.getProperty("password");
    	type(confirmPasswordTextField, conPassword);
    	return conPassword;
    }
    
    public void enterStatusDD()
    {
    	click(statusDropdown);
    }
    
    public void enterStatusOption()
    {
    	click(optionOfStatus);
    }
    
    public void enterSubmit()
    {
    	click(submitButton);
    }
}

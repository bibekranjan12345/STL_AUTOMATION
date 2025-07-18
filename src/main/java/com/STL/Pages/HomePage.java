package com.STL.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import com.STL.Base.Base;

public class HomePage extends Base
{

	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Masters']")
    private WebElement masterDropDown;

    @FindBy(xpath = "//a[normalize-space()='Designation Master']")
    private WebElement desigMasterOption;

    @FindBy(xpath = "//a[normalize-space()='CRCC']")
    private WebElement crccListOption;
    
    public void enterMasterDD()
    {
    	click(masterDropDown);
    }
    
    public void enterDesigMaster()
    {
    	click(desigMasterOption);
    }
    
    public void enterCRCCList()
    {
    	click(crccListOption);
    }

}

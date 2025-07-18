package com.STL.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.STL.Base.Base;

public class CRCCListPage extends Base
{

	public CRCCListPage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath = "//span[.=' Create ']")
	private WebElement createButton;
	
	public void enterCreateButton()
	{
		waitForSpinnerToDisappear();
		click(createButton);
	}

}

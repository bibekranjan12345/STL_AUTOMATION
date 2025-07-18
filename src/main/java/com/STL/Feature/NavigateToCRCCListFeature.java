package com.STL.Feature;

import org.openqa.selenium.WebDriver;

import com.STL.Pages.HomePage;

public class NavigateToCRCCListFeature 
{
	private HomePage homepage;
	
	public NavigateToCRCCListFeature(WebDriver driver)
	{
		homepage=new HomePage(driver);
	}
	
	public void navigateToCRCCListPage()
	{
		homepage.enterMasterDD();
		homepage.enterDesigMaster();
		homepage.enterCRCCList();
	}

}

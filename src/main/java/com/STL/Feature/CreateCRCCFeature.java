package com.STL.Feature;

import org.openqa.selenium.WebDriver;

import com.STL.Pages.CRCCListPage;
import com.STL.Pages.CreateCRCCPage;

public class CreateCRCCFeature 
{
	private CRCCListPage crcclistpage;
	private CreateCRCCPage createcrccpage;
	
	public CreateCRCCFeature(WebDriver driver)
	{
		crcclistpage=new CRCCListPage(driver);
		createcrccpage=new CreateCRCCPage(driver);
		
	}
	
	public void createCRCC() throws InterruptedException
	{
		crcclistpage.enterCreateButton();
		createcrccpage.enterSchoolNameDD();
		createcrccpage.enterSchoolOption();
		createcrccpage.enterCRCCName();
		createcrccpage.enterMobileNo();
		createcrccpage.enterEmail();
		createcrccpage.enterPassword();
		createcrccpage.enterConfirmPassword();
		createcrccpage.enterStatusDD();
		createcrccpage.enterStatusOption();
		createcrccpage.enterSubmit();
	}

}

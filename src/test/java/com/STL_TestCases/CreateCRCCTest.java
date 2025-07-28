package com.STL_TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.STL.Base.BaseTest;
import com.STL.Feature.CreateCRCCFeature;
import com.STL.Feature.NavigateToCRCCListFeature;

public class CreateCRCCTest extends BaseTest
{
	@Test
	public void createCrcc() throws InterruptedException
	{
		CreateCRCCFeature createcrccfeature=new CreateCRCCFeature(driver);
		NavigateToCRCCListFeature navigatetocrccfeature=new NavigateToCRCCListFeature(driver);
		
		navigatetocrccfeature.navigateToCRCCListPage();
		createcrccfeature.createCRCC();
	}

}

package com.STL_TestCases;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.STL.Base.BaseTest;
import com.STL.Constants.Constants;
import com.STL.Feature.NavigateToCRCCListFeature;
import com.STL.Utils.ConfigReader;

public class NavigateToCRCCListTest extends BaseTest
{
	
	@Test
	public void navigateToCRCCListPage()
	{
		NavigateToCRCCListFeature crccListFeature=new NavigateToCRCCListFeature(driver);
		
		crccListFeature.navigateToCRCCListPage();
		Assert.assertEquals(Constants.crccListPageValidation(driver),ConfigReader.getProperty("crccListPageValidation"),"CRCC List Page Validation Unsuccessful");
	}

}

package com.STL_TestCases;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.STL.Base.BaseTest;
import com.STL.Constants.Constants;
import com.STL.Utils.ConfigReader;

public class LoginTest extends BaseTest
{
	@Test
	public void verifyLogin()
	{
		Assert.assertEquals(Constants.loginPageValidation(driver),ConfigReader.getProperty("loginPageValidation"), "User Is Not Able To Login Successfully");
	}
}

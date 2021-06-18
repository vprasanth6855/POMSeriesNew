package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SignUpPageTest extends BaseTest {

	@BeforeClass
	public void signUpPageSetUp(){
		signUpPage = loginPage.navigateToSignUpPage();
	}
	
	@Test
	public void verifyRegisterAccountTest(){
		Assert.assertTrue(signUpPage.registerAccountExist());
	}
	
	@Test
	public void verifyfillOutForm(){
		signUpPage.fillingForm(prop.getProperty("fn"), prop.getProperty("ln"), prop.getProperty("email"), prop.getProperty("tele"), prop.getProperty("pwd"), prop.getProperty("confirm"));
	}
}

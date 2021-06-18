package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.testListeners.ExtentReportListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners(ExtentReportListener.class)
@Epic("Epic 100: define login page features")
@Story("US 101:define the login page class features with title, forgot pwd link and login functionality ")
public class LoginPageTest extends BaseTest{
	
	@Description("verify login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void verifyLoginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println("Title of the page is: "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("verify continue link")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=2)
	public void verifyContinueLinkTest(){
		Assert.assertTrue(loginPage.isContinueLinkExist());
	}
	
	@Description("verify forgotpwd link")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void verifyForgotPassWordLink(){
		Assert.assertTrue(loginPage.isForgotPassWordLinkExist());
	}
	
	@Description("verify navigation")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=5, enabled=false)
	public void verifyNavigateToSignUpPage(){
		loginPage.navigateToSignUpPage();
	}
	
	@Description("verify login")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void verifyLoginTest(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

}

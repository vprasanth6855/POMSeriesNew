package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-200: Design account page")
@Story("US-201:designing the accounts page with title, header, accont sections and product results... ")
public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetUp(){
		accountPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("verify account page title")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void verifyAccountPageTitleTest(){
		String title = accountPage.getHomePageTitle();
		System.out.println("Home page title is: "+title);
		Assert.assertEquals(title, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Description("verify account page header")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void verifyAccountPageHeaderTest(){
		String header = accountPage.getHeaderValue();
		System.out.println("Header value is: "+header);
		Assert.assertEquals(header, Constants.ACCOUNTS_HEADER_VALUE);
	}
	
	@Description("verify account icon")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void verifyAccountIconTest(){
		Assert.assertTrue(accountPage.isAccountIconExist());
	}
	
	@Description("verify account name")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=4)
	public void verifyAccountNameTest(){
		String accountName = accountPage.getAccountNameValue();
		System.out.println("Account Name is: "+accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountName").trim());
	}
	
	@Description("verify account section")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=5)
	public void verifyAccountSectioTest(){
		Assert.assertTrue(accountPage.getAccountSectionCount()==Constants.ACCOUNT_SECTIONS);
	}
	
	@Description("verify account section list")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=6)
	public void verifyAccountSectionList(){
		//accountPage.getAccountSectionList();
		
		Assert.assertEquals(accountPage.getAccountSectionList(), Constants.getAccountSectionList());
	}
	
	@Description("verify account sublinks")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=7)
	public void verifySubLinksTest(){
		Assert.assertEquals(accountPage.subLinksMyAccount(), Constants.subLinksMyAccount());
	}
	
	@Description("verify account search")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=8)
	public void verifySearchTest(){
		Assert.assertTrue(accountPage.doSearch("imac"));;
	}
}

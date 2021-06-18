package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	// By locators

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginButton = By.xpath("//input[@value='Login'and @type='submit'and @class='btn btn-primary']");
	private By continueLink = By.linkText("Continue");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By registerlink = By.linkText("Register");

	// constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// page actions
	
	@Step("getting th elogin page title...")
	public String getLoginPageTitle() {
		
		return elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE, 10);
	}

	@Step("checking continue link...")
	public boolean isContinueLinkExist() {
		return elementUtil.doIsDisplayed(continueLink);
	}

	@Step("checking forgot password...")
	public boolean isForgotPassWordLinkExist() {
		return elementUtil.doIsDisplayed(forgotPasswordLink);
	}

	@Step("checking navigation...")
	public SignUpPage navigateToSignUpPage() {
		elementUtil.doClick(continueLink);

		return new SignUpPage(driver);

	}

	@Step("login with username : {0} and password : {1}")
	public AccountPage doLogin(String un, String pwd) {
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginButton);

		return new AccountPage(driver);
	}
	
	@Step("checking navigation to register...")
	public RegisterPage navigateToRegisterPage(){
		elementUtil.doClick(registerlink);
		return new RegisterPage(driver);
	}

}

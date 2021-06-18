package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage extends BasePage {
	
	ElementUtil elementUtil;
	
	public RegisterPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirm = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]");
	
	private By agreeCheckbox = By.name("agree");
	private By continueButton = By.xpath("//input[@type ='submit' and @value='Continue']");
	
	private By successMessage = By.cssSelector("#content h1");
	private By logout = By.linkText("Logout");	
	
	private By registerLink = By.linkText("Register");
	
	public boolean accountRegistration(String firstname, String lastname, String email, String telephone, String password, String subscribe ){
		elementUtil.doSendKeys(this.firstname, firstname);
		elementUtil.doSendKeys(this.lastname, lastname);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirm, password);
		elementUtil.doSendKeys(this.firstname, firstname);
		
		if(subscribe.equals("yes")){
			elementUtil.doClick(subscribeYes);
		}
		else{
			elementUtil.doClick(subscribeNo);
		}
		
		elementUtil.doClick(agreeCheckbox);
		elementUtil.doClick(continueButton);
		
		String successMsg = elementUtil.doGetText(successMessage);
		if(successMsg.equals(Constants.SUCCESS_MESSAGE)){
			elementUtil.doClick(logout);
			elementUtil.doClick(registerLink);
			return true;
			
		}
		return false;
		
	}

}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.base.BasePage;

public class SignUpPage extends BasePage{
	
	private WebDriver driver;
	
	private By registerAccount = By.xpath("//h1[text()='Register Account']");
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailID = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By passWord = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subScribeRadioButton = By.xpath("//input[@type='radio' and @name='newsletter' and @value='1']");
	private By policyCheckbox = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	
	
	public SignUpPage(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean registerAccountExist(){
		return driver.findElement(registerAccount).isDisplayed();
	}
	
	public void fillingForm(String fn, String ln, String email, String tele, String pwd, String confirm){
		driver.findElement(firstName).sendKeys(fn);
		driver.findElement(lastName).sendKeys(ln);
		driver.findElement(emailID).sendKeys(email);
		driver.findElement(telephone).sendKeys(tele);
		driver.findElement(passWord).sendKeys(pwd);
		driver.findElement(confirmPwd).sendKeys(confirm);
		driver.findElement(subScribeRadioButton).click();
		driver.findElement(policyCheckbox).click();
		driver.findElement(continueButton).click();
		
	}

}

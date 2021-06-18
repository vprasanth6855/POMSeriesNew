package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SignUpPage;

public class BaseTest {
	
	public BasePage basePage;
	public LoginPage loginPage;
	public AccountPage accountPage;
	public SignUpPage signUpPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;
	public Properties prop;
	public WebDriver driver;

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserName){
		basePage = new BasePage();
		prop = basePage.init_prop();
		String browser = prop.getProperty("browser");
	
		if(browserName!=null){
			browser = browserName;
		}
		
		driver = basePage.init_driver(browser);
		loginPage = new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
}

package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountPage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By header = By.linkText("Your Store");
	private By accountIcon = By.xpath("//i[@class='fa fa-user']");
	private By accountName = By.linkText("My Account");
	private By accountHeaders = By.cssSelector("div#content h2");
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By serachButton = By.cssSelector("div#search button[type='button']");
	private By subLinksMyAccount = By.xpath("(//div[@id='content']/ul)[1]/li/a");
	private By searchItemsResult = By.cssSelector(".product-layout .product-thumb");
	private By resultItems = By.cssSelector("#content h4 a");
	
	
	public AccountPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	@Step("home page title verifying...")
	public String getHomePageTitle(){
		return elementUtil.waitForTitlePresent(Constants.ACCOUNTS_PAGE_TITLE, 10);
	}
	
	@Step("header value verifying...")
	public String getHeaderValue(){
		if(elementUtil.doIsDisplayed(header)){
		return elementUtil.doGetText(header);
		}
		return null;
	}
	
	@Step("account icon verifying...")
	public boolean isAccountIconExist(){
		//return driver.findElement(accountIcon).isDisplayed();
		if(elementUtil.doIsDisplayed(accountIcon)){
			return true;
		}
		return false;
	}
	
	@Step("account name verifying...")
	public String getAccountNameValue(){
		if(elementUtil.doIsDisplayed(accountName)){
			return elementUtil.doGetText(accountName);
		}
		return null;
	}
	
	@Step("account section count verifying...")
	public int getAccountSectionCount(){
		return elementUtil.doCountSize(accountHeaders);
	}
	
	@Step("section list verifying...")
	public List<String> getAccountSectionList(){
		List<String> accountList = new ArrayList<String>();
		List<WebElement> accountSectionList = elementUtil.getElements(accountHeaders);
		for(WebElement e : accountSectionList){
			System.out.println(e.getText());
			accountList.add(e.getText());
		}
		return accountList;
	}
	
	@Step("sublinks verifying...")
	public List<String> subLinksMyAccount(){
		List<String> subLinksInMyAccount = new ArrayList<String>();
		List<WebElement> subLinks = elementUtil.getElements(subLinksMyAccount);
		for(WebElement e : subLinks){
			System.out.println(e.getText());
			subLinksInMyAccount.add(e.getText());
		}
		
		return subLinksInMyAccount;
	}
	
	@Step("search verifying: {0}")
	public boolean doSearch(String productname){
		elementUtil.doSendKeys(searchText, productname);
		elementUtil.doClick(serachButton);
		
		if(elementUtil.getElements(searchItemsResult).size()>0){
			return true;
		}
		return false;
	}
	
	@Step("select product verifying:{0} ")
	public ProductInfoPage selectProductFromResults(String productName){
		List<WebElement> resultItemsList = elementUtil.getElements(resultItems);
		System.out.println("Total number of Items displayed: "+ resultItemsList.size());
		for(WebElement e:resultItemsList){
			if(e.getText().equals(productName)){
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}
	
}

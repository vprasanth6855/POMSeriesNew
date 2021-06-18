package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.base.BasePage;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	public ProductInfoPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	private By productNameHeader = By.cssSelector("#content h1");
	private By productMetaData = By.cssSelector("#content ul.list-unstyled:nth-of-type(1) li");
	private By productPrice = By.cssSelector("#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quantity");
	private By addToCartonButton = By.id("button-cart");
	private By productImages = By.cssSelector(".thumbnails li img");
	private By searchText = By.cssSelector("div#search input[name='search']");
	private By successMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	public Map<String, String> getProductInformation(){
		Map<String, String> productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", elementUtil.doGetText(productNameHeader).trim());
		
		List<WebElement> productMetaDataList = elementUtil.getElements(productMetaData);
		for(WebElement e:productMetaDataList){
			productInfoMap.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		
		List<WebElement> productPriceList = elementUtil.getElements(productPrice);
		productInfoMap.put("price",productPriceList.get(0).getText().trim());
		productInfoMap.put(productPriceList.get(1).getText().split(":")[0].trim(), productPriceList.get(1).getText().split(":")[1].trim());
		
		return productInfoMap;
		
	}
	
	public void selectQuantity(String qty){
		elementUtil.doClear(quantity);
		elementUtil.doSendKeys(quantity, qty);
	}
	
	public void addToCart(){
		elementUtil.doClick(addToCartonButton);
	}
	
	public int getproductImages(){
		int imagesCount =  elementUtil.getElements(productImages).size();
		System.out.println("Total images: "+imagesCount);
		return imagesCount;
	}
	
	public void clearTextTest(){
		elementUtil.doClear(searchText);
	}
	
	public String getInoutValueTest(){
		return elementUtil.doGetInputValue(quantity);
	}
	
	public String getSuccessMessage(){
		String success = elementUtil.doGetSuccessMessage(successMessage);
		if(success.contains("Success: You have added")){
			return "Success: You have added";
		}
		return null;
	}
	
	public void waitSuccessMessage(){
		elementUtil.waitForElementpresent(successMessage, 15);
		
	}
	
	public String getproductInfoPageTitle(String productName){
		return elementUtil.waitForTitlePresent(productName, 5);
	}
	
}

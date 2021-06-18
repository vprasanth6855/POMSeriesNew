package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfopageSetup(){
		accountPage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=4)
	public void productInfoPageTitleTest(){
		productInfoPage.clearTextTest();
		accountPage.doSearch("iMac");
		accountPage.selectProductFromResults("iMac");
		Assert.assertEquals(productInfoPage.getproductInfoPageTitle("iMac"), "iMac");
	}
	
	@Test(priority=1)
	public void verifyProductInfoTest_MacBook(){
		
		String productName = "MacBook";
		Assert.assertTrue(accountPage.doSearch(productName));
		
		productInfoPage = accountPage.selectProductFromResults("MacBook Pro");
		
		Assert.assertTrue(productInfoPage.getproductImages()==4);
		
		Map<String,String> productInfoMap = productInfoPage.getProductInformation();
		System.out.println(productInfoMap);
		
		//the map we get is- {Brand=Apple, Availability=In Stock, price=$2,000.00, Ex Tax=$2,000.00, name=MacBook Pro, 
		//Product Code=Product 18, Reward Points=800}
		Assert.assertEquals(productInfoMap.get("name"), "MacBook Pro");
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		Assert.assertEquals(productInfoMap.get("price"), "$2,000.00");
		Assert.assertEquals(productInfoMap.get("Ex Tax"), "$2,000.00");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		Assert.assertEquals(productInfoMap.get("Reward Points"), "800");
		
		
	}
	
	@Test(enabled=false)
	public void verifyProductInfoTest_iMac(){
		productInfoPage.clearTextTest();
		String productName = "iMac";
		Assert.assertTrue(accountPage.doSearch(productName));
		
		productInfoPage = accountPage.selectProductFromResults("iMac");
		
		Assert.assertTrue(productInfoPage.getproductImages()==3);
		
		Map<String,String> productInfoMap = productInfoPage.getProductInformation();
		System.out.println(productInfoMap);
		
		
		Assert.assertEquals(productInfoMap.get("name"), "iMac");
		Assert.assertEquals(productInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(productInfoMap.get("Availability"), "Out Of Stock");
		Assert.assertEquals(productInfoMap.get("price"), "$100.00");
		Assert.assertEquals(productInfoMap.get("Ex Tax"), "$100.00");
		Assert.assertEquals(productInfoMap.get("Product Code"), "Product 14");
		
		
		
	}

	@Test(priority=2)
	public void verifySelectQuantityTest(){
		productInfoPage.selectQuantity("2");
		Assert.assertEquals(productInfoPage.getInoutValueTest().trim(),"2");
	}
	
	@Test(priority=3)
	public void verifyAddToCartTest(){
		productInfoPage.addToCart();
		productInfoPage.waitSuccessMessage();
		Assert.assertEquals(productInfoPage.getSuccessMessage().trim(),"Success: You have added");
	}
	
	
	
	

}

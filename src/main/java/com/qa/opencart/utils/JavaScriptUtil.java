package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	public WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver){
		this.driver = driver;
	}
	/**
	 * get Title by js
	 * @return
	 */
	
	public String getTitleByJs(){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}
	
	/**
	 * To generate an alert
	 * @param message
	 */
	public void generateAlert(String message){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("alert('"+message+"');");
	}
	
	/**
	 * refresh the browser
	 */

	public void refresh(){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("history.go(0);");
	}
	
	/**
	 * finding browser info-useragents
	 */
	public String getBrowserinfo(){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String userAgents = js.executeScript("return navigator.userAgent;").toString();
		return userAgents;
	}
	
	/**
	 * sendKey with id
	 */
	public void sendKeyUsingJSWithId(String id, String uNvalue){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("document.getElementById('"+id+"').value='"+uNvalue+"';");
	}
	
	/**
	 * to click an element
	 */
	public void clickElementByJs(WebElement element){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", element);
	}
	
	/**
	 * to draw a border for an element
	 */
	public void drawBorder(WebElement element){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.border='3px solid red';", element);
	}
	
	/**
	 * draw a border for login form
	 */
	public void drawBorderForLoginTable(WebElement element){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	/**
	 * to make an element flash
	 * 
	 */
	public void flash(WebElement element){               
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for(int i=0; i<15;i++){
			changeColor("rgb(0,200,0", element);//1
			changeColor(bgcolor, element);//2
		}
		
	}
	private void changeColor(String color, WebElement element) {   //noted , this is a part of the above method
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element);
		try{
			Thread.sleep(20);
		}catch(InterruptedException e){
			
		}
	
}
	/**
	 * getting inner Text
	 * @return
	 */
	public String getInnerText(){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String innerText = js.executeScript("return document.documentElement.innerText;").toString();
		return innerText;
	}
	
	/**
	 * to scroll page down
	 */
	public void scrollPageDown(){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}
	
	/**
	 * scroll into view
	 */
	public void scrollIntoView(WebElement element){
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
//	public void dynamicIdTest(){
//		JavascriptExecutor js = ((JavascriptExecutor)driver);
//		js.executeAsyncScript("return document.querySelector("*[type='text']").getAttribute("id");");
//	}
}

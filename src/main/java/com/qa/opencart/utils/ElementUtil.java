package com.qa.opencart.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.base.BasePage;



public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver){
		this.driver=driver;
		jsUtil = new JavaScriptUtil(this.driver);
	}
	
	/**
	 * This method is to create the webElement
	 * @param locator
	 * @return
	 */
	
	public WebElement getElement(By locator){
		WebElement element= driver.findElement(locator);
		if(BasePage.highlight.equals("true")){
			jsUtil.flash(element);
		}
		
		return element;
	}
	public List<WebElement> getElements(By locator){
		return driver.findElements(locator);
	}
	
	/**
	 * This method is to cick on an element
	 * @param locator
	 */
	
	public void doClick(By locator){
		try{
		getElement(locator).click();
		}catch(Exception e){
			System.out.println("some exception occurred");
			System.out.println(e.getMessage());
		}
	}
	/**
	 * click using action class
	 */
	public void doActionClick(By locator){
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.click(element).perform();;
	}
	/**
	 * This method is to pass the values
	 * @param locator
	 * @param value
	 */
	
	public void doSendKeys(By locator, String value){
		try{
		getElement(locator).sendKeys(value);
		}catch(Exception e){
			System.out.println("some exception occurred");
			System.out.println(e.getMessage());
		}
	}
	/**
	 * sendkey using action class
	 */
	public void doActionSendkey(By locator,String value){
		Actions action = new Actions(driver);
		WebElement element = getElement(locator);
		action.sendKeys(element, value).perform();;
	}
	/**
	 * method is to get the text
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator){
		return driver.findElement(locator).getText();
	}
	public int doCountSize(By locator){
		return driver.findElements(locator).size();
	}
	/**
	 * This method is to check if all the elements are visible
	 * @param elements
	 * @param timeOut
	 */
	public void visiblityOfAllElements(List<WebElement> elements, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	/**
	 * This is for title presence explicitly wait
	 * @return 
	 */
	public String waitForTitlePresent(String title, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	/**
	 * method for explicitly wait
	 * @return 
	 */
	public WebElement waitForElementpresent(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return getElement(locator);
	}
	/**
	 * method to check if the element is clickable
	 * @return 
	 */
	public WebElement waitForElementTobeClickable(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return getElement(locator);
	}
	/**
	 * method to check if the element is visible
	 * @return 
	 */
	public WebElement waitForElementTobeVisible(By locator, int timeOut){
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}
	/**
	 * method to check visibility of element located
	 * @return 
	 */
	public WebElement waitForElementVisibilityLocated(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return getElement(locator);
	}
	/**
	 * waiting for url
	 * @return 
	 */
	public String waitForUrl(String url, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}
	/**
	 * wait for alert
	 * @return 
	 */
	public boolean waitForAlertTobePresent(int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}
	/**
	 * method to click when ready
	 */
	public void clickWhenReady(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		getElement(locator).click();
	}
	/**
	 * method to check an element is displayed or not
	 * @return 
	 */
	public boolean doIsDisplayed(By locator){
		return driver.findElement(locator).isDisplayed();
	}
	/**
	 * to clear the text
	 */
	public void doClear(By locator){
		driver.findElement(locator).clear();
	}
	/**
	 * getting the input value from text field
	 */
	public String doGetInputValue(By locator){
		return driver.findElement(locator).getAttribute("value");
	}
	/**
	 * getting the success message
	 */
	public String doGetSuccessMessage(By locator){
		return driver.findElement(locator).getText();
	}
	
}

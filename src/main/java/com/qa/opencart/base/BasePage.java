package com.qa.opencart.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author veena
 *
 */

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to initialize the driver on the basis of given browser
	 * @param browser
	 * @return This returns WebDriver driver
	 */
	
	public WebDriver init_driver(String browser){
		System.out.println("Browser value is: "+ browser);
		
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remoteDriver("chrome");
			}
			else{
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remoteDriver("firefox");
			}
			else{
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
		}
		else if(browser.equals("safari")){
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		else{
			System.out.println("Please pass the correct browser value: "+ browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return getDriver();
	}
	
	/**
	 * getDRiver using Threadlocal
	 * @return 
	 */
	public static synchronized WebDriver getDriver(){
		return tlDriver.get();
	}
	/**
	 * This method is used to load the properties from config.properties
	 * @return It returns Properties prop reference   
	 */
	
	public Properties init_prop(){
		 prop = new Properties();
		 try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/opencart/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return prop;

	}
	
	/**
	 * this method is used to take the screenshot
	 * it will return the path of screenshot
	 * @return 
	 */
	public String getScreenshot(){
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
				}
	/**
	 * for remote webdriver
	 */
	private void init_remoteDriver(String browser) {
		System.out.println("running test on remote grid: "+browser);
		if(browser.equals("chrome")){
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
		
		try {
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if(browser.equals("firefox")){
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
			
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

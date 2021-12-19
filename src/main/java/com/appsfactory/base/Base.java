package com.appsfactory.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.appsfactory.enums.Browsers;
import com.appsfactory.enums.OS;

public class Base {

	public static WebDriver driver;
	
	public WebDriver selectBrowser(String browser) {
		if (System.getProperty("os.name").toLowerCase().contains(OS.WINDOW.name().toLowerCase())) {
			if (browser.equalsIgnoreCase(Browsers.FIREFOX.name())) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}else if(browser.equalsIgnoreCase(Browsers.CHROME.name())){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}
			
		} else if (System.getProperty("os.name").toLowerCase().contains(OS.MAC.name().toLowerCase())) {
			if (browser.equalsIgnoreCase(Browsers.FIREFOX.name())) {
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver/geckodriver");
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}else if(browser.equalsIgnoreCase(Browsers.CHROME.name())) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver/chromedriver");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}
		}
		return driver;
	}
}
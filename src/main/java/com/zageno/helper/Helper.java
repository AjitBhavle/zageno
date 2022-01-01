package com.zageno.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

	Logger logger = LoggerHelper.getLogger(Helper.class);
	
	private WebDriver driver;
	
	public Helper(WebDriver driver){
		this.driver = driver;
	}
	
	public void WaitForElement(WebElement element,long timeOutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void moveToElement(WebElement element){
		Actions action=new Actions(driver);
		action.moveToElement((element)).perform();
	}
	
	public void scrollToElelment(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
}
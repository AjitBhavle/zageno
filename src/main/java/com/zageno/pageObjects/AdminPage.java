package com.zageno.pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cucumber.listener.Reporter;
import com.zageno.helper.Helper;

public class AdminPage {

	private WebDriver driver;
	Properties prop;

	@FindBy(css = "[class='app-ordering module']")
	public WebElement orderingModule;

	@FindBy(css = "[class='model-order'] > th >a")
	public WebElement orderLink;

	@FindBy(css = "#searchbar")
	public WebElement orderSearchInput;

	@FindBy(css = "[id='daas'] > div > input:nth-child(5)")
	public WebElement searchButton;

	@FindBy(css = "#result_list")
	public WebElement productList;

	@FindBy(css = "[class='field-identifier'] > a")
	public WebElement productLink;

	@FindBy(css = "#id_po_number")
	public WebElement poNumber;

	Helper helper;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
		// helper.WaitForElement(userName, 60);
	}

	// clicks methods
	public void clickOnOrderLink() {
		if (this.orderLink.isDisplayed()) {
			this.orderLink.click();
			Reporter.addStepLog("Clicked on orderLink");
		}
	}
	public void clickOnSearchButton() {
		if (this.searchButton.isDisplayed()) {
			this.searchButton.click();
			Reporter.addStepLog("Clicked on searchButton");
		}
	}
	public void clickOnProductLink() {
		if (this.productLink.isDisplayed()) {
			this.productLink.click();
			Reporter.addStepLog("Clicked on productLink");
		}
	}

	// Boolean methods
	public boolean isAdminHomePageDisplayed() {
		Reporter.addStepLog("Admin home page is displayed...");
		return this.orderingModule.isDisplayed();
	}

	public boolean isProductSearchPageDisplayed() {
		return this.productList.isDisplayed() && this.searchButton.isDisplayed();
	}

	public boolean isProductDetailPageDisplayed() {
		return this.poNumber.isDisplayed();
	}
	
	//Enter search input
	
	public void enterOrderNumber(String orderNo) {
		if (this.orderSearchInput.isDisplayed()) {
			this.orderSearchInput.clear();
			this.orderSearchInput.sendKeys(orderNo);
			Reporter.addStepLog("Order number enter in search input box is:" + orderNo);
		}
	}

	// Get text methods
	public String get_PO_Number() {
		String number = null;
		if (this.poNumber.isDisplayed()) {
			number = this.poNumber.getAttribute("value");
		}
		return number;
	}

	// Wait methods
	public void waitUntilHomePageToLoad() {
		helper.WaitForElement(orderingModule, 5);
	}

	public void waitUntilProductDetailPageToLoad() {
		helper.WaitForElement(poNumber, 5);
	}

	public void waitUntilProductListToLoad() {
		Reporter.addStepLog("WaitUntil product list to load");
		helper.WaitForElement(productList, 5);
	}

	// Hover mouse to element methods
	public void scrollToOrderingModule() {
		if (this.orderingModule.isDisplayed()) {
			helper.scrollToElelment(orderingModule);
			Reporter.addStepLog("Scrolled till orderingModule");
		}
	}
	
	//Action method

}

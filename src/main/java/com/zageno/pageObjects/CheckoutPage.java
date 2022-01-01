package com.appsfactory.pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.appsfactory.helper.WaitHelper;
import com.cucumber.listener.Reporter;

public class CheckoutPage {

	private WebDriver driver;
	Properties prop;

	@FindBy(css = "#nav-cart")
	public WebElement cartIcon;

	@FindBy(css = "#sc-active-cart > div")
	public WebElement cartItems;

	@FindBy(css = "#sc-subtotal-amount-activecart > span")
	public WebElement actualCartTotal;

	@FindBy(css = "#sc-buy-box-ptc-button > span > input")
	public WebElement proceedToBuyButton;

	@FindBy(css = "#createAccountSubmit")
	public WebElement createAmazonAccountButton;

	@FindBy(xpath = "//*[@id='search']/div/div/div/span/div/div/div/span/div/div/div[2]/div[3]/div/a/span[1]/span[2]/span[2]")
	public WebElement productList;

	WaitHelper waitHelper;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}

	// clicks method
	public void clickCheckoutBasket() {
		if (this.cartIcon.isDisplayed())
			this.cartIcon.click();
		Reporter.addStepLog("Clicked on cart icon");
	}

	public void clickOnProceedToBuyButton() {
		if (this.proceedToBuyButton.isDisplayed())
			this.proceedToBuyButton.click();
		Reporter.addStepLog("Clicked on proceed to buy button");
	}

	// Boolean methods
	public boolean isCartItemsDisplayed() {
		Reporter.addStepLog("Checked is cart item displayed");
		return this.cartItems.isDisplayed();
	}

	public boolean isCreateAmazonAccountButtonDisplayed() {
		if (this.createAmazonAccountButton.isDisplayed()) {
			Reporter.addStepLog("User redirected to Login page");
			return this.createAmazonAccountButton.isDisplayed();
		} else {
			Reporter.addStepLog("Login page is not displayed");
			return false;
		}
	}

	// get methods
	public double getActualCheckoutTotal() {

		String checkoutTotal = this.actualCartTotal.getText();
		checkoutTotal = checkoutTotal.substring(1);
		Reporter.addStepLog("actual checkoutTotal is: " + Double.parseDouble(checkoutTotal));
		return Double.parseDouble(checkoutTotal);
	}

	public double getExpectedTotal() {
		double total = 0;
		String price;
		List<WebElement> prodPriceLocator = driver.findElements(By.xpath("//*[contains(@class,'sc-product-price')]"));
		for (int k = 0; k < prodPriceLocator.size(); k++) {
			price = prodPriceLocator.get(k).getText().substring(1);
			total = total + Double.parseDouble(price);
		}
		Reporter.addStepLog("Item expected Total:" + total);
		return total;
	}

	public boolean validateCartTotal() {
		boolean value = false;
		if (getActualCheckoutTotal() == getExpectedTotal()) {
			value = true;
			Reporter.addStepLog("Total is same: " + value);
		} else {
			value = false;
		}
		return value;
	}

	// Wait methods
	public void waitUntilCartPageToLoad() {
		waitHelper.WaitForElement(cartItems, 3000);
		Reporter.addStepLog("Wait until cart page to load");
	}

	public void waitUntilLoginPageToLoad() {
		Reporter.addStepLog("WaitUntil login page to displayed");
		waitHelper.WaitForElement(createAmazonAccountButton, 3000);
	}

}

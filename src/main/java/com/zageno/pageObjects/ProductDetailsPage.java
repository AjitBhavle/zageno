package com.zageno.pageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Assert;
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

public class ProductDetailsPage {

	private WebDriver driver;
	Properties prop;
	
	@FindBy(css = "[class='header-menu__item paragraph paragraph--medium js-menu-marketplace']")
	protected WebElement marketplace;

	@FindBy(xpath = "//*[@id='main-content']/div/div[2]/div[1]/div[2]/div[2]/div/div/div/a")
	protected WebElement firstItemViewAndBuyBtn;
	
	@FindBy(css = "[name='quantity-select']")
	public WebElement quantityDropDown;
	
	@FindBy(css = "[class='btn btn--slim cart-btn js-add-to-cart']")
	public WebElement addToCartButton;

	@FindBy(css = "[class*='product__details'] > a > div")
	public WebElement productList;
	
	@FindBy(css = "[class='icon icon--cart']")
	public WebElement addToCartIcon;
		
	@FindBy(css = "[class='header-menu__badge badge badge--success js-badge-cart-count']")
	public WebElement cartCount;
	
	@FindBy(css = "[class*='btn btn--block']")
	public WebElement reviewCartButton;
	
	@FindBy(css = "[class='pdp__column pdp__column--main'] > [class='pdp__header']")
	public WebElement productName;
	
	@FindBy(css = "[class='pdp__column pdp__column--main'] > [class='pdp__tags']")
	public WebElement productTagList;

	@FindBy(css = "[class='pdp__column pdp__column--main'] > [class='pdp-group suppliers']")
	public WebElement supplierGroup;
	
	Helper helper;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
		// helper.WaitForElement(userName, 60);
	}
	
	//clicks methods
	public void clickOnMarketplace() {
		if (this.marketplace.isDisplayed()){
			Reporter.addStepLog("Clicked on marketplace menu option");
			this.marketplace.click();	
		}
	}
	public void clickOnViewAndBuyButton() {
		if (this.firstItemViewAndBuyBtn.isDisplayed()) {
			Reporter.addStepLog("Clicked on firstItemViewAndBuyBtn");
			this.firstItemViewAndBuyBtn.click();
		}
	}
	public void clickOnAddToCartButton() {
		if (this.addToCartButton.isDisplayed()) {
			Reporter.addStepLog("Clicked on addToCartButton");
			this.addToCartButton.click();
		}
	}
	public void clickOnReviewCartButton() {
		if (this.reviewCartButton.isDisplayed()) {
			Reporter.addStepLog("Clicked on reviewCartButton");
			this.reviewCartButton.click();
		}
	}
	public void clickOnAddToCartIconButton() {
		if (this.addToCartIcon.isDisplayed()) {
			Reporter.addStepLog("Clicked on addToCartIcon");
			this.addToCartIcon.click();
		}
	}
	// Boolean methods
	public boolean isProductDetailsDisplayed() {
		Reporter.addStepLog("Product detail page is displayed...");
		return this.productName.isDisplayed() && this.productTagList.isDisplayed() && this.supplierGroup.isDisplayed();
	}

	public boolean isCartCountDisplayed() {
		return this.cartCount.isDisplayed();
	}

	// Select dropDown value
	public void selectQuantityDropDownValue() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		prop.load(fis);
		String quantity=this.generateRandomNumberBetween2And9();
		Select fruits = new Select(quantityDropDown);
		fruits.selectByVisibleText(quantity);
		Reporter.addStepLog("Product quantity selected as: "+quantity);
	}
	
	public String generateRandomNumberBetween2And9(){
		Random r = new Random();
		int low = 3;
		int high = 9;
		int result = r.nextInt(high-low) + low;
		return String.valueOf(result);
	}

	// Wait methods
	
	public void waitUntilHomePageToLoad() {
		helper.WaitForElement(marketplace, 5);
	} 
	
	public void waitUntilProductDetailPageToLoad() {
		helper.WaitForElement(addToCartButton, 5);
	}

	public void waitUntilProductListToLoad() {
		Reporter.addStepLog("WaitUntil product list to load");
		helper.WaitForElement(productList, 5);
	}
	
	public void waitUntilCartCountToLoad() {
		Reporter.addStepLog("WaitUntil cart to load");
		helper.WaitForElement(cartCount, 5);
	}

	// Hover mouse to element methods
	public void hoverToCartIcon() {
		if(this.addToCartIcon.isDisplayed()) {
			helper.moveToElement(addToCartIcon);
			Reporter.addStepLog("Hover mouse to addToCartIcon");
		}
	}
	
	//Action methods
	
	public void select_the_product_and_add_to_cart() throws IOException {
		this.waitUntilHomePageToLoad();
		this.clickOnMarketplace();
		this.waitUntilProductListToLoad();
		this.clickOnViewAndBuyButton();
		this.waitUntilProductDetailPageToLoad();
		Assert.assertTrue("Is product detail page dislayed", this.isProductDetailsDisplayed());
		this.selectQuantityDropDownValue();
		this.clickOnAddToCartButton();
		this.waitUntilCartCountToLoad();
		Assert.assertTrue("Is product added to cart", this.isCartCountDisplayed());
		this.hoverToCartIcon();
		this.clickOnAddToCartIconButton();
	}

}

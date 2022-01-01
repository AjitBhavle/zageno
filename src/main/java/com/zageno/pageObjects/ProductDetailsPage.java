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

public class HomePage {

	private WebDriver driver;
	Properties prop;
	@FindBy(css = "#twotabsearchtextbox")
	protected WebElement searchBox;

	@FindBy(css = "#nav-search-submit-button")
	protected WebElement searchIcon;

	@FindBy(css = "#a-autoid-0-announce")
	public WebElement sortByOption;

	@FindBy(css = "#s-result-sort-select")
	public WebElement sortByOptionDropDown;

	@FindBy(css = "#add-to-cart-button")
	public WebElement addToCartButton;

	@FindBy(css = "#nav-cart-count")
	public WebElement cartCount;

	@FindBy(xpath = "//*[@id='search']/div/div/div/span/div/div/div/span/div/div/div[2]/div[3]/div/a/span[1]/span[2]/span[2]")
	public WebElement productList;

	WaitHelper waitHelper;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		// waitHelper.WaitForElement(userName, 60);
	}

	public void enterSearchText(String text) {
		if (this.searchBox.isDisplayed()) {
			Reporter.addStepLog("Product search input text box is displayed");
			this.searchBox.clear();
			this.searchBox.sendKeys(text);
		}
			
	}

	public void clickOnSearchIcon() {
		if (this.searchIcon.isDisplayed())
			Reporter.addStepLog("Clicked on search icon to search for product");
			this.searchIcon.click();
	}

	public void clickOnSortByOprion() {
		if (this.sortByOption.isDisplayed())
			this.sortByOption.click();
	}

	public void clickOnAddToCartButton() {
		if (this.addToCartButton.isDisplayed())
			this.addToCartButton.click();
	}

	// Boolean methods

	public boolean isSearchIconDisplayed() {
		return this.searchIcon.isDisplayed();
	}

	public boolean isCartCountDisplayed() {
		return this.cartCount.isDisplayed();
	}

	// Select dropDown value
	public void selectByPriceLowToHigh() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		prop.load(fis);
		Select fruits = new Select(sortByOptionDropDown);
		fruits.selectByVisibleText(prop.getProperty("sortByOption"));
		Reporter.addStepLog("Sort prodcts by: "+prop.getProperty("sortByOption"));
	}

	// Wait methods

	public void waitUntilHomePageToLoad() {
		Reporter.addStepLog("WaitUntil home page to load");
		waitHelper.WaitForElement(searchBox, 3000);
	}

	public void waitUntilProductsToLoad() {
		Reporter.addStepLog("WaitUntil product list to load on home page");
		waitHelper.WaitForElement(sortByOptionDropDown, 3000);
	}

	public void waitUntilProductDetailPageToLoad() {
		waitHelper.WaitForElement(addToCartButton, 3000);
	}

	public void waitUntilProductListToLoad() {
		Reporter.addStepLog("WaitUntil sorted product list to load");
		waitHelper.WaitForElement(productList, 3000);
	}

	//In this method using the TreeMap where we are adding all snickers or skittles values to TreeMap.
	//TreeMap automatically sorts the values by descending order that is its functionality
	//After sorting values we are just clicking on first element from map and adding it to cart
	public void selectCheapestItem(String item) throws InterruptedException {
		TreeMap<Integer, WebElement> snickersMap = new TreeMap<Integer, WebElement>();
		TreeMap<Integer, WebElement> skittlesMap = new TreeMap<Integer, WebElement>();
		List<WebElement> prodTextLocator = driver.findElements(
				By.xpath("//*[@id='search']/div/div/div/span[3]/div/div/div/span/div/div/div[2]/div[1]/h2/a/span"));
		List<WebElement> prodPriceLocator = driver.findElements(By.xpath(
				"//*[@id='search']/div/div/div/span/div/div/div/span/div/div/div[2]/div[3]/div/a/span[1]/span[2]/span[2]"));
		String parentHandle = driver.getWindowHandle();
		for (int k = 0; k < prodPriceLocator.size(); k++) {
			if (item.toLowerCase().contains("snickers")) {
				System.out.println("snickers....");
				snickersMap.put(Integer.valueOf(prodPriceLocator.get(k).getText()), prodTextLocator.get(k));
				System.out.println("Lowest snickers product name is: " + prodTextLocator.get(k).getText()
						+" And price is: "+Integer.valueOf(prodPriceLocator.get(k).getText()));
				
				Reporter.addStepLog("Lowest snickers name is: " + prodTextLocator.get(k).getText()
						+" And price is: "+ Integer.valueOf(prodPriceLocator.get(k).getText()));
				
				snickersMap.firstEntry().getValue().click();
				break;
			}
			if (item.toLowerCase().contains("skittles")) {
				System.out.println("skittles....");
				skittlesMap.put(Integer.valueOf(prodPriceLocator.get(k).getText()), prodTextLocator.get(k));
				System.out.println("skittles snickers name is: " + prodTextLocator.get(k).getText()
						+" And price is: "+ Integer.valueOf(prodPriceLocator.get(k).getText()));
				
				Reporter.addStepLog("skittles snickers Price is: " + prodTextLocator.get(k).getText()
						+" And price is: "+ Integer.valueOf(prodPriceLocator.get(k).getText()));
				skittlesMap.firstEntry().getValue().click();
				break;
			}
		}
		for (String winHandle : driver.getWindowHandles()) {
			if (!parentHandle.equals(winHandle)) {
				driver.switchTo().window(winHandle);
				waitUntilProductDetailPageToLoad();
				clickOnAddToCartButton();
				System.out.println("Item added to cart....");
				Reporter.addStepLog("Item added to cart....");
				Thread.sleep(2000);
				driver.close();
			}
		}
		driver.switchTo().window(parentHandle);

	}

}

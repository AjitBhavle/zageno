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

public class CheckoutPage {

	private WebDriver driver;
	Properties prop;

	@FindBy(css = "[name='quantity-select']")
	public WebElement getQuantity;

	@FindBy(css = ".card__inner > div:nth-child(1)")
	public WebElement cartItems;

	@FindBy(css = "[class='checkout-summary__value checkout-summary__value--total']")
	public WebElement actualCartTotal;

	@FindBy(css = "[class='checkout-summary__section checkout-summary__section--details'] >div:nth-child(1) > div:nth-child(2)")
	public WebElement actualSubTotal;

	@FindBy(css = "[class='checkout-summary__section checkout-summary__section--details'] >div:nth-child(2) > div:nth-child(2)")
	public WebElement actualTaxTotal;

	@FindBy(css = "[name='checkout']")
	public WebElement checkoutBtn;

	@FindBy(css = "[class='js-expired-price']")
	public WebElement productPrice;

	@FindBy(css = "#id_po_number")
	public WebElement poNumber;

	@FindBy(css = "#id_cost_center")
	public WebElement costCenterDropdown;

	@FindBy(css = "[class='btn btn--block checkout-container__buy-now js-buy-now']")
	public WebElement agreeAndPurchaseBtn;

	@FindBy(css = "[class*='card__inner group-card__inner'] > div:nth-child(2) > span")
	public WebElement orderNumber;

	@FindBy(css = "[class='sidebar js-sidebar'] > div > section:nth-child(2) > div:nth-child(1) > div:nth-child(2)")
	public WebElement get_PO_Number;

	Helper waitHelper;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new Helper(driver);
	}

	// clicks method
	public void clickOnAgreeAndPurchaseBtnButton() {
		if (this.agreeAndPurchaseBtn.isDisplayed()) {
			this.agreeAndPurchaseBtn.click();
			Reporter.addStepLog("Clicked on agreeAndPurchaseBtn");
		}
	}

	public void clickOnCheckoutButton() {
		if (this.checkoutBtn.isDisplayed()) {
			this.checkoutBtn.click();
			Reporter.addStepLog("Clicked on checkoutBtn");
		}
	}

	// Enter text to input box methods
	public void enter_PO_Number() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		if (this.poNumber.isDisplayed()) {
			this.poNumber.clear();
			this.poNumber.sendKeys(String.valueOf(number));
			Reporter.addStepLog("PO Number enter in input box is:" + String.valueOf(number));
		}

	}

	// Boolean methods
	public boolean isCostCenterDefaultDropDownValueSelected() throws IOException {
		boolean value = false;
		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		prop.load(fis);

		Select select = new Select(costCenterDropdown);
		WebElement option = select.getFirstSelectedOption();
		String defaultSelectedValue = option.getText().trim();
		System.out.println(defaultSelectedValue + " " + prop.getProperty("costCenter"));
		if (defaultSelectedValue.equals(prop.getProperty("costCenter"))) {
			value = true;
			Reporter.addStepLog("Default cost center value selected as: " + defaultSelectedValue);
		}
		return value;
	}

	public boolean isCartItemsDisplayed() {
		Reporter.addStepLog("Cart item displayed...");
		return this.cartItems.isDisplayed();
	}

	public boolean isAgreeAndPurchaseBtnDisplayed() {
		Reporter.addStepLog("agreeAndPurchaseBtn displayed...");
		return this.agreeAndPurchaseBtn.isDisplayed() && this.get_PO_Number.isDisplayed();
	}

	public boolean isOrderNumberDisplayed() {
		boolean value = false;
		if (this.orderNumber.isDisplayed()) {
			value = this.orderNumber.isDisplayed();
			Reporter.addStepLog("User is on confirmation page and OrderNumber is displayed");
		}
		return value;
	}

	// Get methods
	public int getProductQuantity() {
		Select select = new Select(getQuantity);
		WebElement option = select.getFirstSelectedOption();
		return Integer.parseInt(option.getText().trim());
	}

	public double getProductPrice() {
		String productPrice = this.productPrice.getText().replace(",", "").substring(2);
		return Double.valueOf(productPrice);
	}

	public double getActualTax() {
		String actualTaxTotal;
		if(this.actualTaxTotal.getText().length()>1) {
			actualTaxTotal = this.actualTaxTotal.getText().replace(",", "");	
			actualTaxTotal = actualTaxTotal.substring(2);
			
		}else{
			actualTaxTotal="0";
		}
		Reporter.addStepLog("actual actualTaxTotal is: " + Double.valueOf(actualTaxTotal));
		return Double.valueOf(actualTaxTotal);
	}

	public double getActualCheckoutTotal() {
		double number;
		String checkoutTotal = this.actualCartTotal.getText().replace(",", "");
		checkoutTotal = checkoutTotal.substring(2);
		number=Double.valueOf(checkoutTotal);
		Reporter.addStepLog("actual checkoutTotal is: " +number );
		return number;
	}

	public double getActualCheckoutSubTotal() {
		double number;
		String checkoutSubTotal = this.actualSubTotal.getText().replace(",", "");
		checkoutSubTotal = checkoutSubTotal.substring(2);
		System.out.println(checkoutSubTotal);
		number =Double.valueOf(checkoutSubTotal);
		Reporter.addStepLog("actual actualSubTotal is: " +number );
		return number;
	}

	public String getOrderNumber() {
		String orderNumber = this.orderNumber.getText().trim();
		Reporter.addStepLog("Generated order number is: " + orderNumber);
		return orderNumber;
	}

	public String getOrder_PO_Number() {
		String poNumber = null;
		if (this.get_PO_Number.isDisplayed()) {
			String orderPONumber = this.get_PO_Number.getText().trim();
			Reporter.addStepLog("Expected order PO number is: " + orderPONumber);
			poNumber = orderPONumber;
		}
		return poNumber;
	}

	public double getExpectedSubTotal() {
		double total;
		total = this.getProductPrice() * this.getProductQuantity();
		Reporter.addStepLog("Item expected sub total: " + total);
		return total;
	}
	
	public double getExpectedTotal() {
		double total;
		total = this.getExpectedSubTotal() + this.getActualTax();	
		Reporter.addStepLog("Item expected total: " + total);
		return total;
	}
	
	public boolean validateCartSubTotal() {
		boolean value = false;
		if (getActualCheckoutSubTotal() == getExpectedSubTotal()) {
			value = true;
			Reporter.addStepLog("Cart expected sub-total is: " + getExpectedSubTotal()+" "+"Cart actual sub-total is: "+getActualCheckoutSubTotal());
		} else {
			value = false;
		}
		return value;
	}

	public boolean validateCartTotal() {
		boolean value = false;
		if (getActualCheckoutTotal() == getExpectedTotal()) {
			value = true;
			Reporter.addStepLog("Cart expected total is: " + getExpectedTotal()+" "+"Cart actual total is: "+getActualCheckoutTotal());
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

	public void waitUntil_PO_NumberFieldToLoad() {
		waitHelper.WaitForElement(poNumber, 3000);
		Reporter.addStepLog("Wait until poNumber field to load");
	}

	public void waitUntilAddressAndPaymentPageToLoad() {
		waitHelper.WaitForElement(agreeAndPurchaseBtn, 3000);
		Reporter.addStepLog("Wait until address and payment page to load");
	}

	public void waitUntilConfirmationPageToLoad() {
		waitHelper.WaitForElement(orderNumber, 3000);
		Reporter.addStepLog("Wait until order confirmation page to load");
	}

}

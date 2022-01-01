package com.zageno.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.listener.Reporter;
import com.zageno.base.Base;
import com.zageno.helper.Helper;
import com.zageno.pageObjects.AdminPage;
import com.zageno.pageObjects.CheckoutPage;
import com.zageno.pageObjects.LoginPage;
import com.zageno.pageObjects.ProductDetailsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ZagenoCheckoutStepDefinitions extends Base{
	
	LoginPage loginPage = new LoginPage(driver);
	ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
	CheckoutPage checkout = new CheckoutPage(driver);
	AdminPage admin=new AdminPage(driver);
	Helper waitHelper = new Helper(driver);
	public static String order_PO_Number;
	public static String orderNumber;
	
	@Given("^Navigate to url \"([^\"]*)\"$")
	public void navigate_to_url(String arg1){
		driver.get(arg1);
	}
	
	@Given("^Login with Okta$")
	public void login_with_Okta() throws Throwable {		
		loginPage.waitUntilOktaPageToLoad();
		loginPage.clickOnLoginWitOktaBtn();
		loginPage.loginToBackEndApplication();
		System.out.println(order_PO_Number);
		System.out.println(orderNumber);
	}
	@Given("^Login to applicatio with username and password$")
	public void login_to_applicatio_with_username_and_password() throws Throwable {
		loginPage.loginToFrontEndApplication();
	}
	
	@Then("^Validate cart total$")
	public void validate_cart_total(){
		//checkout.clickCheckoutBasket();
		checkout.waitUntilCartPageToLoad();
		Assert.assertTrue("Is cart items are dislayed", checkout.isCartItemsDisplayed());
		Assert.assertTrue("Expected and actual cart total are same: ", checkout.validateCartTotal());
	}
	
	@When("^Select the product and add to cart$")
	public void select_the_product_and_add_to_cart() throws Throwable {		
		detailsPage.select_the_product_and_add_to_cart();
		checkout.waitUntilCartPageToLoad();
		Assert.assertTrue("Is cart items are dislayed", checkout.isCartItemsDisplayed());
	}

	@Then("^Click on checkout button$")
	public void click_on_checkout_button() throws Throwable {
		checkout.clickOnCheckoutButton();
	}
	
	@Then("^Validate cart total and cost center$")
	public void validate_cart_total_and_cost_center() throws Throwable {
		Assert.assertTrue("Validate cart sub-total", checkout.validateCartSubTotal());
		Assert.assertTrue("Validate cart total", checkout.validateCartTotal());
		Assert.assertTrue("Is cost center drop down value selected", checkout.isCostCenterDefaultDropDownValueSelected());
	}

	@Then("^Add PO number to order$")
	public void add_PO_number_to_order() throws Throwable {
		checkout.waitUntil_PO_NumberFieldToLoad();
		checkout.enter_PO_Number();
	}

	@Then("^Get order number$")
	public void get_order_number() throws Throwable {
		checkout.waitUntilAddressAndPaymentPageToLoad();
		order_PO_Number = checkout.getOrder_PO_Number();
		Assert.assertTrue("Is aggree and purchase button displayed", checkout.isAgreeAndPurchaseBtnDisplayed());
		checkout.clickOnAgreeAndPurchaseBtnButton();
		checkout.waitUntilConfirmationPageToLoad();
		orderNumber = checkout.getOrderNumber();
		Assert.assertTrue("Is order number displayed", checkout.isOrderNumberDisplayed());
	}

	@Then("^Search the product number and validate PO number on product detail page$")
	public void Search_the_product_number_and_validate_PO_number_on_product_detail_page() throws Throwable {
		admin.waitUntilHomePageToLoad();
		Assert.assertTrue("Admin home page is displayed", admin.isAdminHomePageDisplayed());
		admin.scrollToOrderingModule();
		admin.clickOnOrderLink();
		admin.waitUntilProductListToLoad();
		Assert.assertTrue("Admin search page is displayed", admin.isProductSearchPageDisplayed());
		admin.enterOrderNumber(orderNumber);
		admin.clickOnSearchButton();
		admin.waitUntilProductListToLoad();
		admin.clickOnProductLink();
		admin.waitUntilProductDetailPageToLoad();
		Assert.assertEquals("Validate PO number same as order placed", admin.get_PO_Number(), order_PO_Number);
		Reporter.addStepLog("PO Number from front-end application is:" + order_PO_Number + " "+"PO Number from Back-end application is:" +admin.get_PO_Number());
	}
	
	@Then("^Adding test scenaio$")
	public void adding_test_scenaio(){
		Reporter.addStepLog("This is Happy Testing sceanrio added for extent report testing...");
	}
	
	
}
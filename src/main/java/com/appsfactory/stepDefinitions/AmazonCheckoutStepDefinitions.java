package com.appsfactory.stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import com.appsfactory.base.Base;
import com.appsfactory.helper.WaitHelper;
import com.appsfactory.pageObjects.CheckoutPage;
import com.appsfactory.pageObjects.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.cucumber.listener.Reporter;


public class AmazonCheckoutStepDefinitions extends Base {

	HomePage homePage = new HomePage(driver);
	CheckoutPage checkout = new CheckoutPage(driver);
	WaitHelper waitHelper = new WaitHelper(driver);
	
	@Given("^Navigate to url \"([^\"]*)\"$")
	public void navigate_to_url(String arg1){
		driver.get(arg1);
		homePage.waitUntilHomePageToLoad();
		Assert.assertTrue("User has landed on page",false);
		 //homePage.isSearchIconDisplayed()
	}
	
	@When("^Search for cheapest \"([^\"]*)\" and add to cart$")
	public void Search_for_cheapest_and_add_to_cart(String item) throws IOException, InterruptedException{
		homePage.enterSearchText(item);
		homePage.clickOnSearchIcon();
		homePage.waitUntilProductsToLoad();
		homePage.selectByPriceLowToHigh();
		homePage.waitUntilProductListToLoad();
		homePage.selectCheapestItem(item);
	}
	
	@Then("^Validate cart total$")
	public void validate_cart_total(){
		checkout.clickCheckoutBasket();
		checkout.waitUntilCartPageToLoad();
		Assert.assertTrue("Is cart items are dislayed", checkout.isCartItemsDisplayed());
		Assert.assertTrue("Expected and actual cart total are same: ", checkout.validateCartTotal());
	}
	
	@Then("^Click on checkout button$")
	public void click_on_checkout_button(){
		checkout.clickOnProceedToBuyButton();
	}
	
	@Then("^User should redirect to login Or registration page$")
	public void user_should_redirect_to_login_page(){
		checkout.waitUntilLoginPageToLoad();
		Assert.assertTrue("Amazon login page dislayed", checkout.isCreateAmazonAccountButtonDisplayed());
	}
	
	@Then("^Adding test scenaio$")
	public void adding_test_scenaio(){
		Reporter.addStepLog("This is Happy Testing sceanrio added for extent report testing...");
	}
	
	
}
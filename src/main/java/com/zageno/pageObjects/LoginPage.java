package com.zageno.pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cucumber.listener.Reporter;
import com.zageno.helper.Helper;

public class LoginPage {

	private WebDriver driver;
	Helper waitHelper;
	Properties prop;

	@FindBy(css = "[class='main-header__register-btn btn btn--dialog btn--slim btn--small']")
	protected WebElement loginBtn;

	@FindBy(css = "#idp-discovery-username")
	protected WebElement usernameInputBox;

	@FindBy(css = "#idp-discovery-submit")
	public WebElement nextBtn;

	@FindBy(css = "#okta-signin-password")
	public WebElement passwordInputBox;

	@FindBy(css = "#okta-signin-submit")
	public WebElement signInBtn;

	@FindBy(css = ".searchbar__input-row")
	public WebElement homeSearchInputBox;
	
	@FindBy(css = "#content > a")
	public WebElement loginWitOkta;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new Helper(driver);
		prop = new Properties();
		// waitHelper.WaitForElement(userName, 60);
	}

	public void enterUsernameText(String text) {
		if (this.usernameInputBox.isDisplayed()) {
			Reporter.addStepLog("Username input text box is displayed");
			this.usernameInputBox.clear();
			this.usernameInputBox.sendKeys(text);
		}

	}

	public void enterPasswordText(String text) {
		if (this.passwordInputBox.isDisplayed()) {
			Reporter.addStepLog("Passwordinput text box is displayed");
			this.passwordInputBox.clear();
			this.passwordInputBox.sendKeys(text);
		}

	}

	public void clickOnLoginBtn() {
		if (this.loginBtn.isDisplayed()) {
			this.loginBtn.click();
			Reporter.addStepLog("Clicked on loginBtn...");
		}
	}

	public void clickOnNextBtn() {
		if (this.nextBtn.isDisplayed()) {
			this.nextBtn.click();
			Reporter.addStepLog("Clicked on nextBtn...");
		}
	}

	public void clickOnSignInBtn() {
		if (this.signInBtn.isDisplayed()){
			this.signInBtn.click();
			Reporter.addStepLog("Clicked on signInBtn...");
		}
	}
	
	public void clickOnLoginWitOktaBtn() {
		if (this.loginWitOkta.isDisplayed()){
			this.loginWitOkta.click();
			Reporter.addStepLog("Clicked on loginWitOkta...");
		}
	}

	// Boolean methods
	public boolean isHomeSearchInputBoxDisplayed() {
		boolean value=false;
		if(this.homeSearchInputBox.isDisplayed()) {
			Reporter.addStepLog("Home Search Input Box is displayed");
			value=true;
		}		
		return value;
	}
	
	public boolean isLoginBtnDisplayed() {
		boolean value=false;
		if(this.loginBtn.isDisplayed()) {
			Reporter.addStepLog("loginBtn is displayed");
			value=true;
		}		
		return value;
	}

	// Wait methods
	public void waitUntilHomePageToLoad() {
		Reporter.addStepLog("WaitUntil home page to load");
		waitHelper.WaitForElement(homeSearchInputBox, 5000);
	}

	public void waitUntilLoginBtnLoad() {
		Reporter.addStepLog("WaitUntil loginBtn to load");
		waitHelper.WaitForElement(loginBtn, 3000);
	}
	
	public void waitUntilNextBtnLoad() {
		Reporter.addStepLog("WaitUntil nextBtn to load");
		waitHelper.WaitForElement(nextBtn, 3000);
	}

	public void waitUntilPasswordPageToLoad() {
		waitHelper.WaitForElement(signInBtn, 3000);
	}
	
	public void waitUntilOktaPageToLoad() {
		waitHelper.WaitForElement(loginWitOkta, 3000);
	}

	public void loginToFrontEndApplication() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		prop.load(fis);
		this.waitUntilLoginBtnLoad();
		this.clickOnLoginBtn();
		this.waitUntilNextBtnLoad();
		this.enterUsernameText(prop.getProperty("username"));
		this.clickOnNextBtn();
		this.waitUntilPasswordPageToLoad();
		this.enterPasswordText((prop.getProperty("password")));
		this.clickOnSignInBtn();
		this.waitUntilHomePageToLoad();
	}
	
	public void loginToBackEndApplication() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		prop.load(fis);
		this.waitUntilNextBtnLoad();
		this.enterUsernameText(prop.getProperty("username"));
		this.clickOnNextBtn();
		this.waitUntilPasswordPageToLoad();
		this.enterPasswordText((prop.getProperty("password")));
		this.clickOnSignInBtn();
	}
	
}

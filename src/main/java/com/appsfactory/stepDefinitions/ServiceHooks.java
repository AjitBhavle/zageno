package com.appsfactory.stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.appsfactory.base.Base;
import com.appsfactory.enums.Browsers;
import com.appsfactory.helper.LoggerHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;



public class ServiceHooks {

	Base testBase;
    Properties prop;
	Logger log = LoggerHelper.getLogger(ServiceHooks.class);
	
	@Before
	public void initializeTest() throws InterruptedException, IOException {
		testBase = new Base();
	
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		prop.load(fis);
	
		// mvn test -Dbrowser=chrome
		String browserName=System.getProperty("browser"); // Uncomment this line if
		// you are sending parameter from Maven
		//String browserName = prop.getProperty("browser");// comment this line if you are sending parameter from Maven
		System.out.println(browserName);
		if (browserName.contains("chrome")) {
			testBase.selectBrowser(Browsers.CHROME.name());
		} else if (browserName.equals("firefox")) {
			testBase.selectBrowser(Browsers.FIREFOX.name());
			// firefox code
		} else if (browserName.equals("IE")) {
			testBase.selectBrowser(Browsers.IE.name());
		}
		Reporter.assignAuthor("Ajit Bhavle");
		
	}

	@After
	public void endTest(Scenario scenario) {
		if (scenario.isFailed()) {

			try {
				log.info(scenario.getName() + " is Failed");
				final byte[] screenshot = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "htmlReportsAndScreenshots/screenshots/png"); // ... and embed it in
			} catch (WebDriverException e) {
				e.printStackTrace();
			}

		} else {
			try {
				log.info(scenario.getName() + " is pass");
				scenario.embed(((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.BYTES), "htmlReportsAndScreenshots/screenshots/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Base.driver.quit();
	}
}
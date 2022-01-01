package com.zageno.stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.zageno.base.Base;
import com.zageno.enums.Browsers;
import com.zageno.helper.LoggerHelper;

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
	public void endTest(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {

			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				//This takes a screenshot from the driver at save it to the specified location
				File sourcePath = (((TakesScreenshot) Base.driver)).getScreenshotAs(OutputType.FILE);
				
				//Building up the destination path for the screenshot to save
				//Also make sure to create a folder 'screenshots' with in the cucumber-report folder
				File destinationPath = new File(System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName + ".png");
				
				//Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath);   

				//This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			} 

		} else {
				log.info(scenario.getName() + " is passed");
		}
		Base.driver.quit();
	}
}
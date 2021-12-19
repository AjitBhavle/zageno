package com.appsfactory.runner;
import java.io.File;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/homePage.feature", glue = { "com/appsfactory/stepDefinitions" }, 
plugin = { "com.cucumber.listener.ExtentCucumberFormatter:reports/cucumber-reports/report.html"},
		monochrome = true, dryRun = false)
public class TestRunner {
	
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "//src//test//resources//extent-config.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
	    Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
	    Reporter.setSystemInfo("Machine", 	System.getProperty("os.name"));
	    Reporter.setSystemInfo("Selenium", "3.11.0");
	    Reporter.setSystemInfo("Java Version", System.getProperty("java.version"));
	    
	}
}
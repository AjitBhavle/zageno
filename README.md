
# Create automation framework and place order from front-end application and validate same order in back-end application

## Description: 

- Set Up a Web Testing Project for Chrome that executes the following Test Case.
- As a new Amazon user, I want to search for the cheapest Snickers and Skittles on the page.
- Add the cheapest ones to your Basket and check if the basket calculates the result correctly
- Check if on Checkout, without an account, the user gets redirected to the registration page.

## **Pre-requisite:**

- JDK 8+ should be installed
- Maven path should be set in system path(**MAVEN**)
- System veriable path should be set "**JAVA_HOME**"
- All maven dependency should be installed from pom.xml file
- Clone repository from [here](https://github.com/AjitBhavle/zageno.git)

## **Usage**

- For maven, add the following as dependency in pom.xml file and save the file. This will download all the dependency from maven central repository and store it on local(.m2 folder in user directory):

		<!-- These are the cucumber dependencies -->
		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>1.2.5</version>
		</dependency>
		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>1.2.5</version>
		</dependency>
		<dependency>
			<groupId>info.cukes</groupId>
			<artifactId>cucumber-testng</artifactId>
			<version>1.2.5</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
		<dependency>
			<groupId>org.seleniumhq.selenium</groupId>
			<artifactId>selenium-java</artifactId>
			<version>3.11.0</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/log4j/log4j -->
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>
		<!-- Extent report -->
		<dependency>
			<groupId>com.aventstack</groupId>
			<artifactId>extentreports</artifactId>
			<version>3.1.2</version>
		</dependency>
		<dependency>
			<groupId>com.vimalselvam</groupId>
			<artifactId>cucumber-extentsreport</artifactId>
			<version>3.0.2</version>
		</dependency>
		
# Multiple Browser support:

  - This framework supports for multiple browser as you need to change browser parameter in "data.prperties" file(**/zageno/src/test/resources/data.prperties**)
  - Currently only "chromedriver and firefox" support added for execution if you want to execute test cases on IE browser then add "ie driver" to "**/drivers/IEDriver**" folder.

# Execution steps:

  ## Command line Execution:   
  - Clone the repo and navigate to project directory.
  - Enter below command on command line and execute your TC.
     - **mvn test -Dbrowser=chrome**
  - **Note: For command line execution no need to make changes in code.**

  ## From eclipse execution: 
  - Before doing eclipse execution please do below changes in ServiceHook.java file(/appsfactory/src/main/java/com/appsfactory/stepDefinitions/)
   	- If you are executing test cases from editor(Eclipse) then make below changes in "ServiceHook.java" file changes.
       	- Comment below line if you are executing test cases from editor. 
     	 	**(String browserName=System.getProperty("browser");)**
       	- Uncomment this line if you are sending parameter from Maven **(String browserName = prop.getProperty("browser");)**
  -  Right click on "TestRunner.xml" file(**/zageno/src/test/java/com/zageno/runner**) and run as **JunitTest** then you will see execution on console.
  -  Html report will get generated in "**/zageno/reports/cucumber-reports/**" folder.
  -  Screenshots will get generated in "**/zageno/reports/screenshots/**" folder.
     
# Framework Folder structure:
![sample](https://github.com/AjitBhavle/zageno/blob/master/images/framework.PNG)

  ## Base package: 
  - You will find driver initialization methods under below folder
     '**/zageno/src/main/java/com/zageno/base**'
 ## Enum package: 
  - You will find all enum constants(e.g OS,Browsers) under below folder
     '**/zageno/src/main/java/com/zageno/enum**'
  ## Helper package: 
  - You will find all common methods used in framework under below folder path.
     '**/zageno/src/main/java/com/zageno/helper**'
  ## POM package: 
  - You will find all pages locators under below folder path.
     '**/zageno/src/main/java/com/zageno/pageObject**'  
  ## stepDefinitions package: 
  - You will find all feature steps implemeted under below folder path.
     '**/zageno/src/main/java/com/zageno/stepDefinitions**'  
  ## Runner package: 
  - This will be the starting point for test execution where we define the feature files path and glue code path.
     '**/zageno/src/test/java/com/zageno/runner**' 
  ## Resources package: 
  - You will find all feature files, extent report config and data related files(data.peroperties) under below folder path.
     '**/zageno/src/test/resources**'
  ## Reports ans screenshots: 
  - You will find all test cases execution html report and failure screenshots under below folder
     '**/zageno/reports/cucumber-reports/**'
     '**/zageno/reports/screenshots/**'
  ## Driver exe files:
   - You will find driver exe files under below path
     '**/zageno/driver**' 

## Execution Summary: 
Please find below screenshot where you can see the test cases are executed successfully.
   ![sample](https://github.com/AjitBhavle/zageno/blob/master/images/executionSummary1.PNG)
   ![sample](https://github.com/AjitBhavle/zageno/blob/master/images/executionSummary2.PNG)
   
## Screenshots on extent report: 
- User can able to see the failure screenshots on reports. see below
![sample](https://github.com/AjitBhavle/zageno/blob/master/images/ReportScreenshot.PNG)
![sample](https://github.com/AjitBhavle/zageno/blob/master/images/screenshotOnReport.PNG)
	
## For Extent report generation and capturing screenshots refer to below link
[Extent report](https://www.toolsqa.com/selenium-cucumber-framework/cucumber-extent-report/)


# Challenge 1: Web automation framework setup and scenarios creation
## Description: 
- Set Up a Web Testing Project for Chrome that executes the following Test Case.
- As a new Amazon user, I want to search for the cheapest Snickers and Skittles on the page.
- Add the cheapest ones to your Basket and check if the basket calculates the result correctly
- Check if on Checkout, without an account, the user gets redirected to the registration page.
## **Pre-requisite:**
- JDK 8+ should be installed
- Maven path should be set in system path(MAVEN)
- System veriable path should be set "JAVA_HOME"
- All maven dependency should be installed from pom.xml file
- Clone repository from [here](https://github.com/AjitBhavle/appsFactory.git)

## **Usage**

- For maven, add the following as dependency in pom.xml file and save the file. This will download all the dependency from maven central repo and store it on local(.m2 folder in user directory):
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

  - This framework supports for multiple browser as you need to change browser parameter in "data.prperties" file(/appsfactory/src/test/resources/data.prperties)
  - Currently only "chromedriver and firefox" support added for execution.

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
  -  Right click on "TestRunner.xml" file(**/appsfactory/src/test/java/com/appsfactory/runner**) and run as **JunitTest** then you will see execution on console.
  -  Html report will get generated in "**/appsfactory/reports/cucumber-reports/**" folder.
  -  Screenshots will get generated in "**/appsfactory/reports/screenshots/**" folder.
     
# Framework Folder structure:
![sample](https://github.com/AjitBhavle/appsFactory/blob/master/images/framework.PNG)

  ## Base package: 
  - You will find driver initialization methods under below folder
     '**/appsfactory/src/main/java/com/appsfactory/base**'
 ## Base package: 
  - You will find all enum constants(e.g OS,Browsers) under below folder
     '**/appsfactory/src/main/java/com/appsfactory/enum**'
  ## Helper package: 
  - You will find all common methods used in framework under below folder path.
     '**/appsfactory/src/main/java/com/appsfactory/helper**'
  ## POM package: 
  - You will find all pages locators under below folder path.
     '**/appsfactory/src/main/java/com/appsfactory/pageObject**'  
  ## stepDefinitions package: 
  - You will find all feature steps implemeted under below folder path.
     '**/appsfactory/src/main/java/com/appsfactory/stepDefinitions**'   
  ## Resources package: 
  - You will find all feature files, extent report config and data related files(data.peroperties) under below folder path.
     '**/appsfactory/src/test/resources**'
  ## Reports ans screenshots: 
  - You will find all test cases execution html report and failure screenshots under below folder
     '**/appsfactory/reports/cucumber-reports/**'
     '**/appsfactory/reports/screenshots/**'
  ## Driver exe files:
   - You will find driver exe files under below path
     '**/appsfactory/driver**' 

## Execution Summary: 
Please find below screenshot where you can see the test cases are executed successfully.
   ![sample](https://github.com/AjitBhavle/appsFactory/blob/master/images/extentReport.PNG)
   
## Screenshots on extent report:
![sample](https://github.com/AjitBhavle/appsFactory/blob/master/images/Screenshot.PNG)
![sample](https://github.com/AjitBhavle/appsFactory/blob/master/images/Screenshot1.PNG)
	
## For Extent report generation and capturing screenshots refer to below link
[Extent repor](https://www.toolsqa.com/selenium-cucumber-framework/cucumber-extent-report/)

# Challenge 2:  propose an Automated Test Setup for a iOS and Android Native App Project.

## Below is the folder structure as a framework creation part:

- **Config**: Here we can keep android and ios app config related data and browser configurable data.
- **Report**: This folder will contains the generated test result in html format.
- **POM** (page object model): This folder contains all web pages related locators and methods tobe use on step definition.
- **Spec**: where we can write test cases in descriptive format and use different methods written in pom to accomplished test case functionality.
- **Helper**: which contains navigation methods, contants and util files.

## Recommended tools:
	- WebdriverIO
	- Appium
	- TypeScript

## Benifits of webdriverIO:
- Open Source
- Works Across Multiple OS
- Cross Browser Compatibility Testing
- Cross-Device Testing
- Easy to Implement
- Supports Multiple Frameworks and Languages
- Add-ons and Reusability
- Server Starting Not Required:
	A major benefit of automation testing with Selenium WebDriver is that you don’t need to start any server prior to testing. The commands written in the 	code are interpreted directly into Webservices and the remote driver receives them via HTTP requests which can then be executed into the browser, consequently 	sending the response.

## Why use WebdriverIO for Automation Testing?

A common reason to use WebdriverIO is its ability to test native mobile applications for iOS-enabled devices. It has a simple structure, and one can write the test scripts concisely – a matter of great convenience for QAs.

WebdriverIO can be easily integrated with third-party testing solution providers like BrowserStack. This makes it easy for the QA to leverage additional functions like recording tests, using real devices and browsers on the cloud, test reporting, etc.

WebdriverIO allows QAs to add new custom commands in addition to those already available by default. By calling the addCommand function, one can add new custom commands that can make testing easier.

## Note: 
**For more details you can visit to below repository**
([Repository link](https://github.com/AjitBhavle/mobileFramework))


# CI pipeline:

First thing we need to do is to integrate our source code control repository(github/bitbucket) with jenkins. 

Please follow below steps to integrate that:
	
## Setting up Continuous Integration with Git Jenkins
- Step 1: go to your GitHub repository and click on ‘Settings’.
- Step 2: Click on Webhooks and click on ‘Add webhook’.
- Step 3: In the ‘Payload URL’ field, paste your Jenkins environment URL. At the end of this URL add /github-webhook/. In the ‘Content type’ select: ‘application/json’ and leave the ‘Secret’ field empty
		![image](https://github.com/AjitBhavle/appsFactory/blob/master/images/webhook.PNG)
- Step 4: On the same page select action on which you want to trigger the jenkins build(e.g pull request or push or pull)
		![image](https://github.com/AjitBhavle/appsFactory/blob/master/images/Actions.PNG)		
	
## Creating Your First Jenkins Job Integrated Into a Test Project
	
- Step 1: Click on “New Item” in the Jenkins user interface dashboard on the left side.
		![image](https://github.com/AjitBhavle/appsFactory/blob/master/images/newItem.PNG)
- Step 2: Create a Freestyle project in Jenkins.
- 		![image](https://github.com/AjitBhavle/appsFactory/blob/master/images/FreeStyleProj.PNG)
- Step 3: Click on the ‘Source Code Management’ tab.
- Step 4: Click on Git and paste your GitHub repository URL in the ‘Repository URL’ field.
	Here, you need to ocnfigure github with genkins either using github secret key or github credentials
- Step 5: Click on the ‘Build Triggers’ tab and then on the ‘GitHub hook trigger for GITScm polling’. Or choose the trigger of your choice.
		![image](https://github.com/AjitBhavle/appsFactory/blob/master/images/buildTriggerWebhook.PNG)
		
	**OR** you can trigger jenkins builds periodically as well by proving specific time. 
	See below examples to triger build periodically:

	- By setting the schedule period to 15 13 * * * you tell Jenkins to schedule the build every day of every month of every year at the 15th minute of the 13th hour of the 	 day.
	- Jenkins used a cron expression, and the different fields are:

		- MINUTES Minutes in one hour (0-59)
		- HOURS Hours in one day (0-23)
		- DAYMONTH Day in a month (1-31)
		- MONTH Month in a year (1-12)
		- DAYWEEK Day of the week (0-7) where 0 and 7 are sunday
		- If you want to schedule your build every 5 minutes, this will do the job : */5 * * * *
		- If you want to schedule your build every day at 8h00, this will do the job : 0 8 * * *
	
- Step: 6: Build Post actions:
	- Here you can configure what all actions you need to perform once you build got success, failure, skip etc.
	- you can trigger **generated html result** in the form of mail to your team mate or lead or manager.
		

# Challenge 3: Outline which testing technology, tool or practice you think is the “next big thing” in Software Testing.

## 1. Codeless Automated Testing: 
Codeless automation testing is trending market now a days. So non-technical people can also write scripts without having the technical knowledge.
e.g "Karate" is the tool on top of cucumber to automate API's without writing single line of code.

## 2. QAOps 

This solution is about the combination of quality assurance (QA) and IT operations (Ops) into the CI/CD chain. QA procedures should not be separated from the entire start to finish software development process. QAOps means close communication and coordination between team members that will lead to developing a product of the best quality, complying with the deadlines, adding new features in the shortest time possible, and so on. 

The other way is by combining QAOps and DevOps. Continuous testing together with a CI/CD operation chain and continuous coordination between developers and QA experts is an approach that promises to identify errors quicker and fasten the product-to-market process.

## 3. Test Automation:

Now a days most of organization are automating their regression suits to deliver best quality of software to their end customers.
	
Below are the mostly used tools in markets:

- BDD(Behavioral Driven Development): Moslty used tool for automating web application and API services. e.g Cucumber, Karate etc.
Benifit of using this is:
1. Any non technical person can write scenarios in feature files and execute them
2. More readability provided to customers. 
3. Bridges the gap between QA's and BA's/PA's.

- Selenium WebDriver and webdriverIO: This two are also hot in the markets and important thing is those are open source tools to use for automation

- TestNG(Extent reports)/Allure report: We can use this one for test html reports generation and send it accross team.

## 4. Artificial Intelligence for Testing:

Although applying the artificial intelligence and machine learning (AI/ML) approaches to address the challenges in software testing is not new in the software 		research community, the recent advancements in AI/ML with a large amount of data available pose new opportunities to apply AI/ML in testing.

However, the application of AI/ML in testing is still in the early stages. Organizations will find ways to optimize their testing practices in AI/ML.

AI/ML algorithms are developed to generate better test cases, test scripts, test data, and reports. Predictive models would help to make decisions about where, 	what, and when to test. Smart analytics and visualization support the teams to detect faults, to understand test coverage, areas of high risk, etc.

We hope to see more applications of AI/ML in addressing problems such as quality prediction, test case prioritization, fault classification and assignment in 		the upcoming years.	

## 5. Internet Of Things (IoT)
This huge and powerful digital network is quickly developing alongside the 4G and 5G network standards spreading all over the world. QA experts should be 	      prepared to test software developed for completely new sorts of hardware for security, dependability, the convenience of usage, connection with other devices, 	      etc. IoT tech is useful for both individual consumers and organizations.  

	

package com.automation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.automation.utilities.BrowserFactory;
import com.automation.utilities.ConfigDataProvider;
import com.automation.utilities.ExcelDataProvider;
import com.automation.utilities.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	public static WebDriver driver;
	public static ExcelDataProvider excel;
	public static ConfigDataProvider config;
	public static ExtentReports report;
	public static ExtentTest logger;

	@BeforeSuite()
	public void SetUpSuite() {
		Reporter.log("Setting up the reports and Test is getting ready", true);

		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		// Create Object of ExtentHtmlReporter and provide the path where you want to
		// generate the report
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File(System.getProperty("user.dir") + "/Reports/FreeCRM_" + Helper.getCurrentDateTime() + ".html"));
		// Create object of ExtentReports class- This is main class which will create
		// report
		report = new ExtentReports();
		// attach the reporter which we created in Step 1
		report.attachReporter(extent);

		Reporter.log("Setting Done and Test can be started", true);

	}

	@Parameters({"browser","URLtobeTested"})
	@BeforeClass
	public void setUp(String browser, String url)
	{

		Reporter.log("Trying to start browser and getting application ready", true);

		// driver = BrowserFactory.startApplication(driver, config.getBrowser(),
		// config.getStagingURL());

		driver = BrowserFactory.startApplication(driver, browser, url );

		Reporter.log("Browser and application is up and runnning", true);
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
	}

	@AfterMethod()
	public void tearDownMethod(ITestResult result) throws IOException {

		Reporter.log("Test is about to end", true);

		if (result.getStatus() == ITestResult.FAILURE) {
			// Helper.captureScreenshot(driver);

			logger.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.pass("Test Passed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}

		// Flush method will write the test in report- This is mandatory step
		report.flush();

		Reporter.log("Test completed >>>> Report generated", true);
	}

}

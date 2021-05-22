package com.birst.testscripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.birst.testsuite.TestSuiteBase;
import com.birst.utils.Homepage;

public class BirstVerification extends TestSuiteBase {
	Homepage homepage=new Homepage();
	
	@BeforeTest
	public void launchBrowser() {
		// Calling the below function to launch the web browser and open the specified webSite
		openBrowser();
	}
	
	@AfterTest
	public void closeBrowser() {
		// Calling the below function to quit all the current browser windows
		quitBrowser();
	}
	
	@Test
	public void verifyBirst() {
		// Calling the below function to verify the title of the current webPage
		homepage.verifyPageTitle();
		
		// Calling the below function to Enter the text on google search box
		homepage.enterTextOnGoogleSearch();
		
		// Calling the below function to verify whether the google search results are displayed with the desired URL
		homepage.verifySearchResultsAndClick();
		
		// Calling the below function to verify whether the Infor's logo is displayed on the home Page.
		homepage.verifyLogoDisplayed();
		
		// Calling the below function to Click on Resources link
		homepage.clickOnResourcesLink();
		
		// Calling the below function to select the product value from the All Products drop-down list
		homepage.selectProduct();
		
		// Calling the below function to select the asset value from the All Assets drop-down list
		homepage.selectAsset();
		
		// Calling the below function to verify whether the desired output is displayed in the filter results
		homepage.verifyFilterResults();
		
	}

}

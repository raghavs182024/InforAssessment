package com.birst.excercise.testscripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.birst.excercise.testsuite.TestSuiteBase;
import com.birst.excercise.utils.Homepage;

public class BirstVerification extends TestSuiteBase {
	static Homepage homepage=new Homepage();
	@BeforeTest
	public void launchBrowser() {
		openBrowser();
	}
	
	@AfterTest
	public void closeBrowser() {
		quitBrowser();
	}
	
	@Test
	public void verifyBirst() {
		//Title verification after launching browser
		homepage.pageTitleVerfication();
		
		//Enter text on google search
		homepage.enterTextonGoogleSearch();
		
		//Checking same text which available or not
		homepage.verifySearchResultsAndClick();
		
		//Logo verification
		homepage.logoVerification();
		
		//Click on Resources
		homepage.clickOnResource();
		
		//Birst Filter from all products
		homepage.selectProduct();
		
		//Past Webinars filter selection
		homepage.selectAsset();
		
		//Webinars text verification after filtering
		homepage.verifyFilterResults();
		
	}

}

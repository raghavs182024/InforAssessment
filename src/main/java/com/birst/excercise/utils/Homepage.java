package com.birst.excercise.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.birst.excercise.testsuite.TestSuiteBase;


public class Homepage extends TestSuiteBase{	
	Long implicit_wait_time = Long.parseLong(configProp.getProperty("IMPLICIT_WAIT"));
	Long explicit_wait_time = Long.parseLong(configProp.getProperty("EXPLICIT_WAIT"));
	
	// Purpose: This function is to verify the page title of the launched webSite
	public void verifyPageTitle() {
		driver.manage().timeouts().implicitlyWait(implicit_wait_time, TimeUnit.SECONDS);
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	
	// Purpose: This function is to enter the text on google search bar
	public void enterTextOnGoogleSearch() {
		String searchField=homepage.getProperty("google_search_field");
		String searchText=homepage.getProperty("google_search_text");
		
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchField)));
		WebElement searchBox=driver.findElement(By.xpath(searchField));
		searchBox.sendKeys(searchText);
		searchBox.sendKeys(Keys.ENTER);
	}
	
	/* Purpose: This function is to verify the whether the desired URL is displayed in the google search results. 
				And, click on it if it's  displayed.
	*/
	public void verifySearchResultsAndClick() {
		String results=homepage.getProperty("google_search_results_list");
		String textToBePresent=homepage.getProperty("google_search_results_text");
		
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(results)));
		List<WebElement> list=driver.findElements(By.xpath(results));
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getText().toString().equals(textToBePresent)) {
				list.get(i).click();
				break;
			}
		}
		
		String acceptCookies=homepage.getProperty("accept_cookies_btn");
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.id(acceptCookies)));
		driver.findElement(By.id(acceptCookies)).click();
	}	
	
	// Purpose: This function is to verify whether the Infor's logo is displayed on the home page
	public void verifyLogoDisplayed() {
		String logo=homepage.getProperty("infor_logo_img");
//		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logo)));
		Boolean bool=driver.findElement(By.xpath(logo)).isDisplayed();
		Assert.assertTrue(bool);
	}
	
	// Purpose: This function is to click on the resources link from the home page
	public void clickOnResourcesLink() {
		String resourcesLink=homepage.getProperty("resources_link");
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.linkText(resourcesLink)));
		driver.findElement(By.linkText(resourcesLink)).click();
	}
	
	// Purpose: This function is to select the desired product from the "All Products" drop-down list
	public void selectProduct() {
		String dropdown=homepage.getProperty("product_dropdown");
		String dropdownValue=homepage.getProperty("product_value");
		
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropdown)));
		WebElement element=driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		new Select(element).selectByVisibleText(dropdownValue);
	}
	
	// Purpose: This function is to select the asset from the "All Assets" drop-down list
	public void selectAsset() {
		String dropdown=homepage.getProperty("asset_dropdown");
		String dropdownValue=homepage.getProperty("asset_value");
		
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropdown)));
		WebElement element=driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		new Select(element).selectByVisibleText(dropdownValue);
	}
	
	// Purpose: This function is to verify whether the desired text is displayed among the filter results
	public void verifyFilterResults() {
		String filterResults=homepage.getProperty("filter_results_text");
//		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(filterResults)));
		Boolean bool=driver.findElement(By.xpath(filterResults)).isDisplayed();
		Assert.assertTrue(bool);
	}
}

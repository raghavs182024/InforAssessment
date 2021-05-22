package com.birst.excercise.utils;

import java.io.File;
import java.util.List;
import java.util.Properties;
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


public class Homepage  extends TestSuiteBase{
	static Properties homepage=loadProperties("resources"+File.separator+"pageobjects"+File.separator+"HomePage.properties");
	static Properties configProp=loadProperties("resources" + File.separator + "config.properties");
	
	Long implicit_wait_time = Long.parseLong(configProp.getProperty("IMPLICIT_WAIT"));
	Long explicit_wait_time = Long.parseLong(configProp.getProperty("EXPLICIT_WAIT"));
	
	public void pageTitleVerfication() {
		driver.manage().timeouts().implicitlyWait(implicit_wait_time, TimeUnit.SECONDS);
		Assert.assertEquals(driver.getTitle(), "Google");
	}
	
	public void enterTextonGoogleSearch() {
		String searchField=homepage.getProperty("google_search_field");
		String searchText=homepage.getProperty("google_search_text");
		
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchField)));
		WebElement searchBox=driver.findElement(By.xpath(searchField));
		searchBox.sendKeys(searchText);
		searchBox.sendKeys(Keys.ENTER);
	}
	
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
		
		String acceptCookies=homepage.getProperty("btn_accept_cookies");
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.id(acceptCookies)));
		driver.findElement(By.id(acceptCookies)).click();
	}	
	
	public void logoVerification() {
		String image=homepage.getProperty("birst_logo_img");
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(image)));
		Boolean bool=driver.findElement(By.xpath(image)).isDisplayed();
		Assert.assertTrue(bool);
	}
	
	public void clickOnResource() {
		String resources=homepage.getProperty("birst_resources");
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.linkText(resources)));
		driver.findElement(By.linkText(resources)).click();
	}
	
	public void selectProduct() {
		String dropdown=homepage.getProperty("product_dropdown");
		String dropdownValue=homepage.getProperty("product_value");
		
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropdown)));
		WebElement element=driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		new Select(element).selectByVisibleText(dropdownValue);
	}
	
	public void selectAsset() {
		String dropdown=homepage.getProperty("asset_dropdown");
		String dropdownValue=homepage.getProperty("asset_value");
		
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropdown)));
		WebElement element=driver.findElement(By.xpath(dropdown));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		new Select(element).selectByVisibleText(dropdownValue);
	}
	
	public void verifyFilterResults() {
		String filterResults=homepage.getProperty("filter_results");
		new WebDriverWait(driver, explicit_wait_time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(filterResults)));
		Boolean bool=driver.findElement(By.xpath(filterResults)).isDisplayed();
		Assert.assertTrue(bool);
	}
}

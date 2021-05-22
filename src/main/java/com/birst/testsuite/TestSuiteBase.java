package com.birst.testsuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class TestSuiteBase {
	
	public static WebDriver driver;
	
	// Purpose: This function is to load the properties from the given file path
	public static Properties loadProperties(String propertyFilePath ) { 
		 File file=new File(propertyFilePath);
		 
		 FileInputStream fileInput=null; 
		 try {
			fileInput=new FileInputStream(file);
		 }
		 catch(FileNotFoundException e) { 
			 e.printStackTrace(); 
		 } 
		
		 Properties prop=new Properties(); 
		
		 try { 
			prop.load(fileInput); 
		 }catch(IOException e) {
			e.printStackTrace();
		 }
		 
		 return prop; 
	}

	public static Properties homepage=loadProperties("resources"+File.separator+"pageobjects"+File.separator+"HomePage.properties");
	public static Properties configProp=loadProperties("resources" + File.separator + "config.properties");
	
	// Purpose: This function is to launch the desired browser and open the URL
	public void openBrowser() {		
		String Browser = configProp.getProperty("browser");
		String url = configProp.getProperty("url");
		Long wait_time = Long.parseLong(configProp.getProperty("IMPLICIT_WAIT"));
		
		if (Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "lib" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "lib" + File.separator + "geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "lib" + File.separator + "IEdriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(wait_time, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);

	}
	
	// Purpose: This function is to close the browser
	public void quitBrowser() {
		driver.quit();
	}
	 
	 
}

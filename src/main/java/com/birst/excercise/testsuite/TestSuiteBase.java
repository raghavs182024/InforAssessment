package com.birst.excercise.testsuite;

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
	String propertyFilePath = "resources" + File.separator + "config.properties";
	
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

	public void openBrowser() {
		Properties prop = new Properties();
		prop = loadProperties("resources" + File.separator + "config.properties");
		
		String Browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		Long wait_time = Long.parseLong(prop.getProperty("IMPLICIT_WAIT"));
		
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
	
	public void quitBrowser() {
		driver.quit();
	}
	 
	 
}

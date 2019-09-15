package com.portal.test_cases;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckTestEmail {
	
private static WebElement element = null;
	
	public static WebElement goToTestEmail(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[8]"));
		return element;
	}
	
	public static WebElement sendEmail(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div/h3/input"));
		return element;
	}
	
	public static WebElement sentContent(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]"));
		return element;
	}
	
	public static WebElement pickEmail(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"to_email1\"]/option[6]"));
		return element;
	}
	
	public static WebElement invalidAddress(WebDriver driver)throws Exception {
		   try {
		      element = driver.findElement(By.xpath("//*[@id=\"data_divs\"]")); 
		      return element;
		   } catch (Exception e1) {
		      // Add a message to your Log File to capture the error
		     // Logger.error("Link is not found.");
		      System.out.println("Invalid Address.");
		      // Take a screenshot which will be helpful for analysis.
		      File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		      FileUtils.copyFile(screenshot, new File("C:\\Users\\arkan\\Progineer-Workspace\\repository\\1.1\\src\\main\\java\\ScreenShots\\screenshot_"+CheckTestEmail.class.getName()+".jpg"));
		      throw(e1);
		   }
	}
}

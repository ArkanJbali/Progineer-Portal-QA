package com.portal.test_cases;



import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckChecklist {
private static WebElement element = null;
	
	public static WebElement goToChecklist (WebDriver driver) {
		element = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/ul[1]/li[6]"));
		return element;
	}
	public static WebElement printReport(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"add\"]"));
		return element;
	}
	public static WebElement selectGroup(WebDriver driver) {
		element = driver.findElement(By.xpath("//tr[1]//td[5]//div[1]//select[1]//option[6]"));
		return element;
	}
	
	public static WebElement addGroupButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//tr[1]//td[5]//div[1]//input[1]"));
		return element;
	}
	
	public static WebElement removeGroup(WebDriver driver) {
		element = driver.findElement(By.xpath("//tr[1]//td[4]//div[1]//div[3]//input[1]"));
		return element;
	}
	
	public static int rowsCount(WebDriver driver) {
		int rows = driver.findElements(By.xpath("//*[@id=\"data_tp\"]/tbody/tr")).size();
		System.out.println(rows);
		return rows;
	}
	public static WebElement removeCheckList(WebDriver driver) {
		 element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr["+ (rowsCount(driver)) +"]/td[6]/div/a"));
		return element;
	}
	public static boolean isAlertPresent(WebDriver driver) 
	{ 
	    try 
	    { 
	       driver.switchTo().alert();  
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}
	public static String existAlertMessage(WebDriver driver) {
		 Alert alert = (Alert) driver.switchTo().alert();  
	        //Using accept() method to accept the alert box  
		 	String alertTxt = alert.getText();
	       alert.accept();
		return alertTxt;
	}
}

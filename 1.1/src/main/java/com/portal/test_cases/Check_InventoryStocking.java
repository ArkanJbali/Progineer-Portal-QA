package com.portal.test_cases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_InventoryStocking {
private static WebElement element = null;
	
	public static WebElement go_to_InventoryStocking (WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[4]"));
		return element;
	}
	public static int row_Count(WebDriver driver) {
		int rows = driver.findElements(By.xpath("//*[@id=\"data_tp\"]/tbody/tr")).size();
		//System.out.println(rows);
		return rows;
	}
	public static WebElement Check_Who_Active(WebDriver driver) throws InterruptedException {
		int isActive = 0;
		for(int row = 2; row < row_Count(driver)-1 ; row++) {
			element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr["+ row +"]/td[4]/div"));
			//System.out.println(element.getText().toString());
			if(element.getText().toString().equals("Y")) {
				isActive = row;
				System.out.println("#"+isActive+": is Active !!");
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr["+ isActive +"]/td[5]/div/button")).click();
				Thread.sleep(3000);
			}
		}
		if(isActive == 0) {
			driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[2]/td[5]/div/button")).click();
			Thread.sleep(3000);
		}
		return element;
	}
	public static WebElement check_ByButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[2]/td[5]/div/button"));
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
	public static String Deactive_alertMessage(WebDriver driver) {
		 Alert alert = (Alert) driver.switchTo().alert();  
	        //Using accept() method to accept the alert box  
		 	String alertTxt = alert.getText();
	       alert.accept();
		return alertTxt;
	}
}

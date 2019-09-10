package com.portal.test_cases;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_Users {
	
private static WebElement element = null;
	
	public static WebElement go_to_users(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[3]"));
		return element;
	}
	
	public static List<WebElement> go_to_usersPermission(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@title=\"User Permissions\"]"));
		return elements;
	}
	public static WebElement get_CurrentPermission(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id='data_tp']/tbody/tr[" + row + "]/td["+ column +"]/div"));
		return element;
	}
	public static WebElement select_No_Access(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[" + row + "]/td["+ column + "]//*[@id=\"selectingAsset\"]/option[3]"));
		return element;
	}
	public static WebElement select_Read_Only(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[" + row + "]/td["+ column + "]//*[@id=\"selectingAsset\"]/option[1]"));
		return element;
	}
	public static WebElement select_Read_Write(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[" + row + "]/td["+ column + "]//*[@id=\"selectingAsset\"]/option[2]"));
		return element;
	}
	public static WebElement press_Update_Btn(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[" + row + "]/td["+ column + "]/div/button"));
		return element;
	}
	public static void Compare_Permission(WebDriver driver) throws InterruptedException{
		String currentPermission = "";
		//String selectedPermission = "";
		for(int i=1; i<=8; i++) {
			for(int j=2; j<=3 ; j++) {
				if(j == 2) {
					Thread.sleep(2000);
					currentPermission = get_CurrentPermission(driver, i, j).getText().toString();
					Thread.sleep(2000);
					if(!currentPermission.equals("")) {
					if(!currentPermission.equals("Read Only")) {
						Thread.sleep(2000);
						select_Read_Only(driver, i, j+1).click();
						Thread.sleep(2000);
						press_Update_Btn(driver, i, j+1).click();
						Thread.sleep(2000);
						if(isAlertPresent(driver)) {
							Alert alert = (Alert) driver.switchTo().alert();  
							alert.accept();
							Thread.sleep(2000);
						}
					}else if(!currentPermission.equals("Read & Write")) {
						Thread.sleep(2000);
						select_Read_Write(driver, i, j+1).click();
						Thread.sleep(2000);
						press_Update_Btn(driver, i, j+1).click();
						Thread.sleep(2000);
						if(isAlertPresent(driver)) {
							Alert alert = (Alert) driver.switchTo().alert();  
							alert.accept();
							Thread.sleep(2000);
						}
					}else if(!currentPermission.equals("No Access")){
						Thread.sleep(2000);
						select_No_Access(driver, i, j+1).click();
						Thread.sleep(2000);
						press_Update_Btn(driver, i, j+1).click();
						Thread.sleep(2000);
						if(isAlertPresent(driver)) {
							Alert alert = (Alert) driver.switchTo().alert();  
							alert.accept();
							Thread.sleep(2000);
						}
					}
					}
					else {
						continue;
					}
					Thread.sleep(2000);
					//System.out.println("i[" + i + "], j["+ j +"]");
				}
				
			}
		}
		
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
	

}

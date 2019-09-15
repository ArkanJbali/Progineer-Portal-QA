package com.portal.test_cases;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckUsers {
	
private static WebElement element = null;
	
	public static WebElement goToUsers(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[3]"));
		return element;
	}
	
	public static List<WebElement> goToUsersPermission(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@title=\"User Permissions\"]"));
		return elements;
	}
	public static WebElement getCurrentPermission(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id='data_tp']/tbody/tr[" + row + "]/td["+ column +"]/div"));
		return element;
	}
	public static WebElement selectNoAccess(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[" + row + "]/td["+ column + "]//*[@id=\"selectingAsset\"]/option[3]"));
		return element;
	}
	public static WebElement selectReadOnly(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[" + row + "]/td["+ column + "]//*[@id=\"selectingAsset\"]/option[1]"));
		return element;
	}
	public static WebElement selectReadWrite(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[" + row + "]/td["+ column + "]//*[@id=\"selectingAsset\"]/option[2]"));
		return element;
	}
	public static WebElement pressUpdateButton(WebDriver driver,int row, int column) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp\"]/tbody/tr[" + row + "]/td["+ column + "]/div/button"));
		return element;
	}
	public static void comparePermission(WebDriver driver) throws InterruptedException{
		String currentPermission = "";
		//String selectedPermission = "";
		for(int row = 1; row <= 8; row++) {
			for(int column = 2; column <= 3 ; column++) {
				if(column == 2) {
					Thread.sleep(2000);
					currentPermission = getCurrentPermission(driver, row, column).getText().toString();
					Thread.sleep(2000);
					if(!currentPermission.equals("")) {
					if(!currentPermission.equals("Read Only")) {
						Thread.sleep(2000);
						selectReadOnly(driver, row, column+1).click();
						Thread.sleep(2000);
						pressUpdateButton(driver, row, column+1).click();
						Thread.sleep(2000);
						if(isAlertPresent(driver)) {
							Alert alert = (Alert) driver.switchTo().alert();  
							alert.accept();
							Thread.sleep(2000);
						}
					}else if(!currentPermission.equals("Read & Write")) {
						Thread.sleep(2000);
						selectReadWrite(driver, row, column+1).click();
						Thread.sleep(2000);
						pressUpdateButton(driver, row, column+1).click();
						Thread.sleep(2000);
						if(isAlertPresent(driver)) {
							Alert alert = (Alert) driver.switchTo().alert();  
							alert.accept();
							Thread.sleep(2000);
						}
					}else if(!currentPermission.equals("No Access")){
						Thread.sleep(2000);
						selectNoAccess(driver, row, column+1).click();
						Thread.sleep(2000);
						pressUpdateButton(driver, row, column+1).click();
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
	public static List<WebElement> deleteUser(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"list_show\"]/a/img[@title=\"Delete\"]"));
		return elements;
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
	public static WebElement addButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"add_item\"]"));
		return element;
	}
	public static WebElement nameInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		return element;
	}
	public static WebElement userNameInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"usr_name\"]"));
		return element;
	}
	public static WebElement passwordInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
		return element;
	}
	public static WebElement rolePick(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"type\"]/option[2]"));
		return element;
	}
	public static WebElement addNewUserButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Add_item_dataaddform\"]/td[6]/input"));
		return element;
	}
	
	public static WebElement invalidUser(WebDriver driver)throws Exception {
		   try {
		      element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div/table/tbody/tr[1]/td[@class='tcat']")); 
		      return element;
		   } catch (Exception e1) {
		      // Add a message to your Log File to capture the error
		     // Logger.error("Link is not found.");
		      // Take a screenshot which will be helpful for analysis.
		      File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		      FileUtils.copyFile(screenshot, new File("C:\\Users\\arkan\\Progineer-Workspace\\repository\\1.1\\src\\main\\java\\ScreenShots\\screenshot_"+CheckUsers.class.getName()+".jpg"));
		      throw(e1);
		   }
	}
}

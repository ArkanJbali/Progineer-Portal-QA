package com.portal.test_cases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckEmployees {
private static WebElement element = null;
	
	public static WebElement goToAllEmployees(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[1]"));
		return element;
	}
	public static WebElement searchEmployee(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp_2_filter\"]/label/input")); 
		return element;
	}
	
	public static WebElement addButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"add_emp\"]"));
		return element;
	}
	
	public static WebElement nameInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"nameEmp\"]"));
		return element;
	}
	
	public static WebElement IDInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"ID_Number\"]"));
		return element;
	}
	
	public static WebElement startDate(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Starting_Date\"]"));
		return element;
	}
	
	public static WebElement pickLocation(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"location\"]/option[3]"));
		return element;
	}
	
	public static WebElement pickGroup(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"group\"]/option[4]"));
		return element;
	}
	
	public static WebElement pickManagerId(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"select2-manager_id-container\"]"));
		return element;
	}
	
	public static WebElement jobTitle(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"job_title\"]"));
		return element;
	}
	
	public static WebElement isActive(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Add_emp_dataaddform\"]/td[9]"));
		return element;
	}
	
	public static WebElement continueOption(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Add_emp_dataaddform\"]/td[10]/div/input[1]"));
		return element;
	}
	
	public static WebElement doneOption(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Add_emp_dataaddform\"]/td[10]/div/input[2]"));
		return element;
	}
	public static List<WebElement> deactiveButton(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"list_show\"]/a"));
		
		return elements;
	}
	public static WebElement checkListForLeaving(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"btn_17\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_16\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_18\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_19\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_20\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_21\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_22\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_23\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_24\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_25\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_26\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_27\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_28\"]")).click();
		Thread.sleep(1000); 
		driver.findElement(By.xpath("//*[@id=\"btn_29\"]")).click();
		Thread.sleep(1000); 
		return element;
	}
	public static List<WebElement> employeeAssetsButton(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"data_tp_2\"]/tbody/tr/td/a"));
		return elements;
	}
	public static WebElement addHeadset(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@value='Add Headset ']"));
		//element = driver.findElement(By.className("tcat"));
		return element;
	}
	public static WebElement selectHeadset(WebDriver driver) {
		/*Select select = new Select(driver.findElement(By.xpath("//*[@id=\"SelectTag\"]")));
		element = select.getFirstSelectedOption();
		String defaultItem = element.getText();
		System.out.println(defaultItem );
		*/
		//element = driver.findElement(By.xpath("//*[@id=\"SelectTag\"]/option[1]"));
		element = driver.findElement(By.xpath("//*[@id=\"SelectTag\"]"));
		return element;
	}
	public static WebElement addHeadsetButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"myModal_data\"]/div/button"));
		return element;
	}
	
	public static List<WebElement> resetPassword(WebDriver driver) {
		List<WebElement> elements = driver.findElements(By.xpath("//div[2]/a/img"));
		return elements;
	}
	public static boolean isElementPresent(WebDriver driver) {
	    try {
	    	//driver.findElement(By.xpath("//*[@id=\"Add_emp_dataaddform\"]/td[1]"));
	    	//System.out.println("Diminssions:" + driver.findElement(By.xpath("//*[@id=\"Add_emp_dataaddform\"]/td[1]")).getSize().toString());
	        if("(51, 62)".equals(driver.findElement(By.xpath("//*[@id=\"Add_emp_dataaddform\"]/td[1]")).getSize().toString())){
	        	//System.out.println("true");
	        	return true; 
	        }
	        else {
	        	//System.out.println("false");
	    	return false;
	        }
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	    	System.out.println("No such element");
	        return false;
	    }
	}
	public static String getTheCurrentDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		return df.format(dateobj);
	}
	public static String alertMessage(WebDriver driver) {
		 Alert alert = (Alert) driver.switchTo().alert();  
	        //Using accept() method to accept the alert box  
		 	String alertTxt = alert.getText();
	       alert.accept();
		return alertTxt;
	}
	public static String deactiveAlertMessage(WebDriver driver) {
		 Alert alert = (Alert) driver.switchTo().alert();  
	        //Using accept() method to accept the alert box  
		 	String alertTxt = alert.getText();
	       alert.accept();
		return alertTxt;
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

package com.portal.test_cases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_assets {
	
	private static WebElement element = null;
	
	public static WebElement Go_to_Assets(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[2]"));
		return element;
	}
	
	public static WebElement Filter_By_Asset(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"felter\"]/option[12]")); 
		return element;
	}
	
	public static WebElement Add_New_Asset(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div[1]/button[1]"));
		return element;
	}
	
	public static WebElement Asset_Type(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Asset\"]"));
		return element;
	}
	
	public static WebElement Description(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Description\"]"));
		return element;
	}
	
	public static WebElement PTech_tag(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"pTech_Tag_#\"]"));
		return element;
	}
	
	public static WebElement Status(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"status\"]"));
		return element;
	}
	
	public static WebElement Invoice_Source(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Invoice_source\"]"));
		return element;
	}
	
	public static WebElement Date_Of_Purchase(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Date_of_Purchase\"]"));
		return element;
	}
	
	public static WebElement Invoice_Number(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Invoice_number\"]"));
		return element;
	}
	
	public static WebElement Warranty_PeriodY(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Warranty_period/y\"]"));
		return element;
	}
	
	public static WebElement Warranty_PeriodM(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Warranty_period/m\"]"));
		return element;
	}
	
	public static WebElement Expected_end_date(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Expected_end_date_of_Warranty\"]"));
		return element;
	}
	
	public static WebElement Serial_Number(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Serial_number\"]"));
		return element;
	}
	
	public static WebElement Save_Btn(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"add_ass\"]/button"));
		return element;
	}
	
	
	public static boolean isAlertPresent(WebDriver driver) 
	{ 
	    try 
	    { 
	      Alert alert = driver.switchTo().alert();
	       alert.accept();
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	} 
	/******************************** Add New PDF **************************************/
	
	public static WebElement Add_New_PDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div[1]/button[2]"));
		return element;
	}
	
	public static WebElement Host_Name(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Host_name\"]"));
		return element;
	}
	
	public static WebElement ThinkPad_Model(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"ThinkPad_Model\"]"));
		return element;
	}
	
	public static WebElement Model(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Model\"]"));
		return element;
	}
	
	public static WebElement Laptop_Property(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Laptop_Property\"]"));
		return element;
	}
	
	public static WebElement Serial_NumberPDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Serial_number\"]"));
		return element;
	}
	
	public static WebElement Status_PDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"status\"]"));
		return element;
	}
	
	public static WebElement Date_Of_Purchase_PDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Date_of_Purchase\"]"));
		return element;
	}
	
	public static WebElement Save_Btn_PDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"add_pdfLaptop\"]/button"));
		return element;
	}
	
	/******************************** Add New Asset Type **************************************/
	
	public static WebElement new_Asset_Type(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div[1]/button[3]"));
		return element;
	}
	
	public static WebElement Add_Asset_Btn(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"addCauses_of_Death\"]"));
		return element;
	}
	
	public static WebElement type_Input(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"item_extra_data_name\"]"));
		return element;
	}
	public static WebElement type_InputBtn(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"item_extra_dataaddform\"]/td[3]/input"));
		return element;
	}
	
}

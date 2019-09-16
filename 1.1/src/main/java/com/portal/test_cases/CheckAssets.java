package com.portal.test_cases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckAssets {
	
	private static WebElement element = null;
	
	public static WebElement goToAssets(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[2]"));
		return element;
	}
	public static WebElement showEntries(WebDriver driver, int entrie) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp_length\"]/label/select/option["+ entrie +"]"));
		return element;  
	}
	public static void scrollPageDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		element = driver.findElement(By.xpath("//*[@id=\"data_tp_info\"]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	public static void scrollPageUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		element = driver.findElement(By.xpath("//*[@id=\"data_tp_length\"]"));
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	public static WebElement searchAsset(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp_filter\"]/label/input")); 
		return element;
	}
	
	public static WebElement filterByAsset(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"felter\"]/option[12]")); 
		return element;
	}
	
	public static WebElement addNewAsset(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div[1]/button[1]"));
		return element;
	}
	
	public static WebElement assetType(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Asset\"]"));
		return element;
	}
	
	public static WebElement description(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Description\"]"));
		return element;
	}
	
	public static WebElement pTechTag(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"pTech_Tag_#\"]"));
		return element;
	}
	
	public static WebElement status(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"status\"]"));
		return element;
	}
	
	public static WebElement invoiceSource(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Invoice_source\"]"));
		return element;
	}
	
	public static WebElement dateOfPurchase(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Date_of_Purchase\"]"));
		return element;
	}
	
	public static WebElement invoiceNumber(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Invoice_number\"]"));
		return element;
	}
	
	public static WebElement warrantyPeriodY(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Warranty_period/y\"]"));
		return element;
	}
	
	public static WebElement warrantyPeriodM(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Warranty_period/m\"]"));
		return element;
	}
	
	public static WebElement expectedEndDate(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Expected_end_date_of_Warranty\"]"));
		return element;
	}
	
	public static WebElement serialNumber(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Serial_number\"]"));
		return element;
	}
	
	public static WebElement saveButton(WebDriver driver) {
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
	
	public static WebElement addNewPDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div[1]/button[2]"));
		return element;
	}
	
	public static WebElement hostName(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Host_name\"]"));
		return element;
	}
	
	public static WebElement thinkPadModel(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"ThinkPad_Model\"]"));
		return element;
	}
	
	public static WebElement model(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Model\"]"));
		return element;
	}
	
	public static WebElement laptopProperty(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Laptop_Property\"]"));
		return element;
	}
	
	public static WebElement serialNumberPDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Serial_number\"]"));
		return element;
	}
	
	public static WebElement statusPDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"status\"]"));
		return element;
	}
	
	public static WebElement dateOfPurchase_PDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Date_of_Purchase\"]"));
		return element;
	}
	
	public static WebElement saveButtonPDF(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"add_pdfLaptop\"]/button"));
		return element;
	}
	
	/******************************** Add New Asset Type **************************************/
	
	public static WebElement newAssetType(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div[1]/button[3]"));
		return element;
	}
	
	public static WebElement addAssetButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"addCauses_of_Death\"]"));
		return element;
	}
	
	public static WebElement typeInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"item_extra_data_name\"]"));
		return element;
	}
	public static WebElement typeInputButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"item_extra_dataaddform\"]/td[3]/input"));
		return element;
	}
	
}

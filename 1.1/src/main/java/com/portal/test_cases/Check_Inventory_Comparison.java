package com.portal.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_Inventory_Comparison {
private static WebElement element = null;
	
	public static WebElement go_to_InventoryComparison(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[7]"));
		return element;
	}
	
	public static WebElement search_for_Inventory(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp_filter\"]/label/input"));
		return element;
	}
	
	public static WebElement printReport(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"add\"]"));
		return element;
	}
}

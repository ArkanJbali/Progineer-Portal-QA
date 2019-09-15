package com.portal.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckInventoryComparison {
private static WebElement element = null;
	
	public static WebElement goToInventoryComparison(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[7]"));
		return element;
	}
	
	public static WebElement searchForInventory(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp_filter\"]/label/input"));
		return element;
	}
	
	public static WebElement printReport(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"add\"]"));
		return element;
	}
}

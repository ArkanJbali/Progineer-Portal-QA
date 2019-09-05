package com.portal.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_assets {
	
	private static WebElement element = null;
	
	public static WebElement search_emp(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_tp_2_filter\"]/label/input")); 
		return element;
	}
}

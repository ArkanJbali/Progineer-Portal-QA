package com.portal.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckGroups {
private static WebElement element = null;
	
	public static WebElement goToGroups(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[5]"));
		return element;
	}
	
	public static WebElement addNewGroupButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"addingGroup\"]"));
		return element;
	}
	
	public static WebElement groupInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"group_name\"]"));
		return element;
	}
	
	public static WebElement saveButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Add_addCompanyGroup\"]/td[2]/input[2]"));
		return element;
	}
	
}

package com.portal.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_Groups {
private static WebElement element = null;
	
	public static WebElement go_to_Groups(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[5]"));
		return element;
	}
	
	public static WebElement Add_new_Group_Btn(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"addingGroup\"]"));
		return element;
	}
	
	public static WebElement Group_Input(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"group_name\"]"));
		return element;
	}
	
	public static WebElement Save_Btn(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"Add_addCompanyGroup\"]/td[2]/input[2]"));
		return element;
	}
	
}

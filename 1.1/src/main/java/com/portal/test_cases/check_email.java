package com.portal.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class check_email {
	private static WebElement element = null;
	
	public static WebElement get_tag_name(WebDriver driver) {
		element = driver.findElement(By.tagName("h2")); 
		return element;
	}
	public static WebElement get_email_input(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@name='username']")); 
		return element;
	}	
	
	public static WebElement get_password_input(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@name='password']")); 
		return element;
	}
	
	/* ************************************************** */
	public static WebElement get_email_input2(WebDriver driver) {
		element = driver.findElement(By.xpath("//form[@id='log_in_form']/input[1]"));
		return element;
	}
	public static WebElement get_password_input2(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[2]"));
		return element;
	}
	/* ************************************************** */
	
	public static WebElement press_login_btn(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@type='submit']")); 
		return element;
	}
	public static WebElement press_logout_btn(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[@href='http://192.168.2.184/envintory-qa//Login/logout?name=qa2']")); 
		return element;
	}
}

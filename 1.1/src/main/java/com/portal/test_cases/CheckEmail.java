package com.portal.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckEmail {
	private static WebElement element = null;
	
	public static WebElement getTagName(WebDriver driver) {
		element = driver.findElement(By.tagName("h2")); 
		return element;
	}
	public static WebElement getEmailInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@name='username']")); 
		return element;
	}	
	
	public static WebElement getPasswordInput(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@name='password']")); 
		return element;
	}
	
	/* ************************************************** */
	public static WebElement getEmailInput2(WebDriver driver) {
		element = driver.findElement(By.xpath("//form[@id='log_in_form']/input[1]"));
		return element;
	}
	public static WebElement getPasswordInput2(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[2]"));
		return element;
	}
	/* ************************************************** */
	
	public static WebElement pressLoginButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@type='submit']")); 
		return element;
	}
	public static WebElement pressLogoutButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[@href='http://192.168.2.184/envintory-qa//Login/logout?name=qa2']")); 
		return element;
	}
}

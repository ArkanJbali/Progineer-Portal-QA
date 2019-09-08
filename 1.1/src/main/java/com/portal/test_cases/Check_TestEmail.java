package com.portal.test_cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_TestEmail {
private static WebElement element = null;
	
	public static WebElement go_to_TestEmail(WebDriver driver) {
		element = driver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/li[8]"));
		return element;
	}
	
	public static WebElement sendEmail(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]/div/h3/input"));
		return element;
	}
	
	public static WebElement Sent_content(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"data_div\"]"));
		return element;
	}
	
	public static WebElement pick_Email(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id=\"to_email1\"]/option[6]"));
		return element;
	}
}

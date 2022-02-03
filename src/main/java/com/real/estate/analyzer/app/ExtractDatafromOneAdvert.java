package com.real.estate.analyzer.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExtractDatafromOneAdvert {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", 
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.imot.bg/pcgi/imot.cgi?act=5&adv=1b164131625036294&topmenu=5&f1=1&f2=1&f3=a14811880783654031&f5=1&f6=%C2%F1%E8%F7%EA%E8&f7=%C2%F1%E8%F7%EA%E8&f8=2&f9=1");
		Thread.sleep(200);
		
		driver.findElement(By.className("fc-button-label")).click();
		Thread.sleep(200);
		
		String offer = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/strong"))
				.getText();
		
		String place = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/span[1]"))
				.getText();
		
		String price = driver.findElement(By
				.id("cena"))
				.getText();
		
		String date = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/div[3]"))
				.getText();
		
		String squareFootage = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/ul/li[2]"))
				.getText();
		String floor = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/ul/li[4]"))
				.getText();
		
		if (offer != null) {
			
			System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n", offer, place, price, date, squareFootage, floor);
		} else {
			
			System.out.println("Not found");
		}
		
		
	}
	
}

package com.real.estate.analyzer.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExtractDataforOneAdvert {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", 
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		String urlImotBg =  "https://www.imot.bg/pcgi/imot.cgi?act=5&adv=1b164382325993161";
		
		extractDataFromImotBG(driver, urlImotBg);
		
		System.out.println("<---------------------------->");
		
		String urlHomesBg = "https://www.homes.bg/offer/apartament-za-prodazhba/"
				+ "tristaen-89m2-sofiya-zhk.-ljulin-6/as1370158";
		
		extractDataFromHomesBg(driver, urlHomesBg);
	}
	
	private static void extractDataFromHomesBg(
			WebDriver driver, String urlHomesBg) throws InterruptedException {

		String[] parts;
		
		String splitAt = ",";
		
		driver.get(urlHomesBg);
		Thread.sleep(200);
		
		String title = driver.findElement(By
				.className("section-title"))
				.getText();
		
		parts = splitStringAt(title, splitAt);
		
		title = parts[0];
		
		String squareFootage = parts[1].trim();
		
		String price = driver.findElement(By
				.xpath("/html/body/div/div/div[2]/div[2]/div/div/div/div/div/"
						+ "div[2]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td"))
				.getText();
		parts = splitStringAt(price, splitAt);
		price = parts[0] + " " + parts[1];
		
		String floor = driver.findElement(By
				.xpath("/html/body/div/div/div[2]/div[2]/div/div/div/div/div/"
						+ "div[2]/div[2]/div[1]/div/div[2]/span/h3"))
				.getText()
				.substring(0,1);
		
		String address = driver.findElement(By
				.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div/"
						+ "div[2]/div[2]/table/tbody/tr/td[1]"))
				.getText();
		
		parts = splitStringAt(address, splitAt);
		
		address = parts[0];
		
		String city = parts[1]
				.trim();
		
		String broker = driver.findElement(By
				.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div/"
						+ "div[2]/div[2]/div[2]/div[2]/table/tbody/tr[1]/td[2]/table/tbody/tr[2]/td/div[1]"))
				.getText();
		
		
		System.out.printf("%s%n%s%n %s%n %s%n %s%n %s%n %s%n %s%n",
				"Homes.bg", title, address, squareFootage, city, price, floor, broker);
	}

	public static void extractDataFromImotBG(
			WebDriver driver, String url ) throws InterruptedException {
		
		String[] parts;
		
		String splitAt = ",";
		
		int delay= 200;
		
		driver.get(url);
		Thread.sleep(delay);
		
		driver.findElement(By.className("fc-button-label")).click();
		Thread.sleep(delay);
		
		String title = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/strong"))
				.getText();
		
		String address = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/span[1]"))
				.getText();
		
		parts = splitStringAt(address, splitAt);
		
		address = parts[1]
				.trim();
		
		String city = parts[0]
				.substring(5);
		
		String price = driver.findElement(By
				.id("cena"))
				.getText();
		
		String squareFootage = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/ul/li[2]"))
				.getText();
		
		String floor = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[1]/form[2]/div[2]/ul/li[4]"))
				.getText()
				.substring(0,1);
		
		String broker = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[2]/div[1]/div/div/a[2]"))
				.getText();
		
			System.out.printf("%s%n%s%n %s%n %s%n %s%n %s%n %s%n %s%n",
					"Imot.bg", title, address, city, price, squareFootage, floor, broker);
	}
	
	public static String[] splitStringAt(String str, String splitAt) {
		
		String[] parts = str.split(splitAt);
		
		return parts;
	}
}

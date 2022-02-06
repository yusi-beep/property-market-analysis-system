package com.real.estate.analyzer.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.real.estate.analyzer.entity.Advert;

public class WriteDataforAdvertonDb {

	public static void main(String[] args)throws InterruptedException {

		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Advert.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
				
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		String urlImotBg = "https://www.imot.bg/pcgi/imot.cgi?act=5&adv=1b164382325993161";
		
		String urlHomesBg = "https://www.homes.bg/offer/apartament-za-prodazhba/tristaen-89m2-sofiya-zhk.-ljulin-6/"
				+ "as1370158https://www.homes.bg/offer/apartament-za-prodazhba/tristaen-89m2-sofiya-zhk.-ljulin-6/as1370158";
		
			try {
				
				Advert tempAdvert; 
				
				//start a transaction
				session.beginTransaction();
	
				tempAdvert =  extractDataFromImotBg(driver, urlImotBg);
				
				System.out.println("Saving advert: " + tempAdvert);
				session.save(tempAdvert);
				
				System.out.println("<---------------------------->");
				
				tempAdvert = extractDataFromHomesBg(driver, urlHomesBg);	
				
				System.out.println("Saving second advert: " + tempAdvert);
				session.save(tempAdvert); 
				
				//commit transactions
				session.getTransaction().commit();
			
			}finally{
				
			session.close();
			factory.close();
			driver.close();
			}
		}
	
	private static Advert extractDataFromHomesBg(
			WebDriver driver, String urlHomesBg)	throws InterruptedException {
			
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
		
		String price = driver.findElement(By.className("ver20black"))
				.getText();
		
		parts = splitStringAt(price, splitAt);
		price = parts[0] + " " + parts[1];
		 
		 String floor = driver.findElement(By
				.xpath("/html/body/div/div/div[2]/div[2]/div/div/div/div/div/"
						+ "div[2]/div[2]/div[1]/div/div[2]/span/h3"))
				.getText()
				.substring(0, 1);
		
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
		
		Advert tempAdvert = new Advert(
				title, squareFootage, address, city, price, floor, broker);

		return tempAdvert;
	}

	public static Advert extractDataFromImotBg(
			WebDriver driver, String url)	throws InterruptedException {
		
		int delay = 200;
		
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
		
		String[] parts;
		
		String splitAt = ",";
		
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
				.substring(0, 1);
		
		String broker = driver.findElement(By
				.xpath("/html/body/div[2]/table/tbody/tr[1]/td[2]/div[1]/div/div/a[2]"))
				.getText();
		
			Advert tempAdvert = new Advert(
					title, squareFootage, address, city, price, floor, broker);
			
			return tempAdvert;
	}
	
	public static String[] splitStringAt(String str, String splitAt) {
		
		String[] parts = str.split(splitAt);
		
		return parts;
	}
}

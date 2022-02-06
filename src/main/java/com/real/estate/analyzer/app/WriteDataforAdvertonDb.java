package com.real.estate.analyzer.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.real.estate.analizer.dtos.Extract;
import com.real.estate.analizer.dtos.HomesBg;
import com.real.estate.analizer.dtos.ImotBg;
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
				
				Extract extractImotBg = new ImotBg();
				tempAdvert =  extractImotBg.extractData(driver, urlImotBg);
				
				System.out.println("Saving advert: " + tempAdvert);
				session.save(tempAdvert);
				
				System.out.println("<---------------------------->");
				
				Extract extractHomesBg = new HomesBg();
				tempAdvert = extractHomesBg.extractData(driver, urlHomesBg);	
				
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
}

package com.real.estate.analyzer.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;
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
		
		String urlImotBg = "https://www.imot.bg/pcgi/imot.cgi?act=5&adv=1a164388122478136&slink=7ng5u5&f1=1";
		
		String urlHomesBg = "https://www.homes.bg/offer/apartament-za-prodazhba/tristaen-89m2-sofiya-zhk.-ljulin-6/"
				+ "as1370158https://www.homes.bg/offer/apartament-za-prodazhba/tristaen-89m2-sofiya-zhk.-ljulin-6/as1370158";
		
			try {
				
				Advert tempAdvert; 
				
				//start a transaction
				session.beginTransaction();
				
				Connector extractImotBg = new ImotBgConnector();
				tempAdvert =  extractImotBg.extractData(driver, urlImotBg);
				
				System.out.println("Saving advert: " + tempAdvert);
				session.save(tempAdvert);
				
				System.out.println("<---------------------------->");
				
				Connector extractHomesBg = new HomesBgConnector();
				tempAdvert = extractHomesBg.extractData(driver, urlHomesBg);	
				
				System.out.println("Saving second advert: " + tempAdvert);
				session.save(tempAdvert); 
				
				//commit transactions
				session.getTransaction().commit();
			
			} finally {
				
			session.close();
			factory.close();
			driver.close();
			}
		}
}

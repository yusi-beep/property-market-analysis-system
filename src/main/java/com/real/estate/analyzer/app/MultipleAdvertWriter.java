package com.real.estate.analyzer.app;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;
import com.real.estate.analyzer.entity.Advert;

public class MultipleAdvertWriter {

	public static void main(String[] args) throws InterruptedException {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Advert.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
				
		System.setProperty("webdriver.chrome.driver", 
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
			try {
				
				Advert tempAdvert; 
				
				//start a transaction
				session.beginTransaction();
				
				Connector extractHomesBg = new HomesBgConnector();
				
				driver.get(extractHomesBg.getWorkPageUrl(driver));
				
				ArrayList<String> urlHomesBg = (ArrayList<String>) extractHomesBg.urlArray(driver);
				
				for (String url : urlHomesBg) {
					
				tempAdvert =  extractHomesBg.extractData(driver, url);
				
				System.out.println("Saving advert: " + tempAdvert);
				session.save(tempAdvert);
				}
				
				System.out.println("<---------------------------->");
				
				Connector extractImotBg = new ImotBgConnector();
				
				driver.get(extractImotBg.getWorkPageUrl(driver));
				
				ArrayList<String> urlImotBg = (ArrayList<String>) extractImotBg.urlArray(driver);
				
				for (String url : urlImotBg) {
					
					tempAdvert =  extractImotBg.extractData(driver, url);
					
					System.out.println("Saving advert: " + tempAdvert);
					session.save(tempAdvert);
					
				}
				//commit transactions
				session.getTransaction().commit();
			
			} finally {
				
			session.close();
			factory.close();
			driver.close();
			}
	}

}









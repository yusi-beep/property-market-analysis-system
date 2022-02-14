package com.real.estate.analyzer;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.real.estate.analyzer.connectors.Connector;
import com.real.estate.analyzer.connectors.HomesBgConnector;
import com.real.estate.analyzer.connectors.ImotBgConnector;
import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.repository.AdvertRepository;

@SpringBootApplication
@EnableJpaRepositories("com.real.estate.analyzer.repository")
public class PropertyMarketAnalyzerApplication {
	
	private static final Logger log = LoggerFactory.getLogger(PropertyMarketAnalyzerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PropertyMarketAnalyzerApplication.class, args);
		
		Connector connector = new HomesBgConnector();
		
		connector.extractAdverts();

		connector = new ImotBgConnector();
		connector.extractAdverts();		
	}
/*
	@Bean
	public CommandLineRunner testing(AdvertRepository repository) {
		return (args) -> {
			repository.save(new Advert("title", "squareFootage", "price", "floor", "address", "city", "broker", "url", LocalDateTime.now()));
		
			  // fetch all adverts
			  log.info("Customers found with findAll():");
		      log.info("-------------------------------");
		      for (Advert advert : repository.findAll()) {
		        log.info(advert.toString());
		      }
		      log.info("");
		
		      // fetch an individual Advert by ID
		      Advert Advert = repository.findById(1L).orElseThrow(() ->
              new NoSuchElementException("The advert with id: " + 1L + " is not found in the repository.")
      );
		      log.info("Advert found with findById(1L):");
		      log.info("--------------------------------");
		      log.info(Advert.toString());
		      log.info("");
		      
		};
		
	}
*/
}
	
	


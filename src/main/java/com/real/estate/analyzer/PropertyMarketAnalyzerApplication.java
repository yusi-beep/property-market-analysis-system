package com.real.estate.analyzer;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.real.estate.analyzer.entity.Advert;
import com.real.estate.analyzer.jpa.AdvertRepository;
import com.real.estate.analyzer.utils.Utils;

@SpringBootApplication
public class PropertyMarketAnalyzerApplication {
	
	private static final Logger log = LoggerFactory.getLogger(PropertyMarketAnalyzerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PropertyMarketAnalyzerApplication.class, args);
		
		
		Utils.extractAdvertsHomesBg();

		Utils.extractAdvertsImotBg();
		
	}

	private LocalDateTime dateTime;

	@Bean
	public CommandLineRunner demo(AdvertRepository repository) {
		return (args) -> {
			repository.save(new Advert("title", "squareFootage", "price", "floor", "address", "city", "broker", "url", dateTime));
		
			  // fetch all adverts
			  log.info("Customers found with findAll():");
		      log.info("-------------------------------");
		      for (Advert advert : repository.findAll()) {
		        log.info(advert.toString());
		      }
		      log.info("");
		
		      // fetch an individual Advert by ID
		      Advert Advert = repository.findById(1L);
		      log.info("Advert found with findById(1L):");
		      log.info("--------------------------------");
		      log.info(Advert.toString());
		      log.info("");
		};
		
	}

}
	
	


package com.real.estate.analyzer.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.real.estate.analyzer.repository.AdvertRepository;

@Service("advertService")
public class AdvertServiceImpl implements AdvertService/*, CommandLineRunner*/{

		@Autowired
		private AdvertRepository advertRepository;

		@Autowired
	    private ApplicationContext context;
		
		@Override
		public BigDecimal minPriceForNeighbourhood(String name) {
			return advertRepository.minPriceForNeighbourhood(name);
		}

		@Override
		public BigDecimal maxPriceForNeighbourhood(String name) {
			return advertRepository.maxPriceForNeighbourhood(name);
		}

		@Override
		public BigDecimal avgPriceForNeighbourhood(String name) {
			return advertRepository.avgPriceForNeighbourhood(name);
		}
		
		@Override
		public BigDecimal minPriceForAgnecy(String name) {
			return advertRepository.minPriceForAgnecy(name);
		}
		
		@Override
		public BigDecimal maxPriceForAgnecy(String name) {
			return advertRepository.maxPriceForAgnecy(name);
		}

		@Override
		public BigDecimal avgPriceForAgnecy(String name) {
			return advertRepository.avgPriceForAgnecy(name);
		}
/*
		@Override
		public void run(String... args) throws Exception {
			
			AdvertService advertService = context.getBean(AdvertService.class);
			
			System.out.println("The biggest price for Шумен: " + advertService.maxPriceForNeighbourhood("Шумен"));
			System.out.println("The smallest price for Шумен: " + advertService.minPriceForNeighbourhood("Шумен"));
			System.out.println("The smallest price for Шумен: " + advertService.avgPriceForNeighbourhood("Шумен"));
			
			System.out.println("The biggest price for ДОМ: " + advertService.maxPriceForAgnecy("ДОМ"));
			System.out.println("The smallest price for ДОМ: " + advertService.minPriceForAgnecy("ДОМ"));
			System.out.println("The smallest price for ДОМ: " + advertService.avgPriceForAgnecy("ДОМ"));
		}
*/
}

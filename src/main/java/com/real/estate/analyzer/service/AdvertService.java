package com.real.estate.analyzer.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.real.estate.analyzer.repository.AdvertRepository;

@Service
public class AdvertService {

		@Autowired
		private AdvertRepository advertRepository;

		public BigDecimal minPriceForNeighbourhood(Long id) {
			return advertRepository.minPriceForNeighbourhood(id);
		}

		public BigDecimal maxPriceForNeighbourhood(Long id) {
			return advertRepository.maxPriceForNeighbourhood(id);
		}

		public BigDecimal avgPriceForNeighbourhood(Long id) {
			return advertRepository.avgPriceForNeighbourhood(id);
		}
		
		public BigDecimal minPriceForAgnecy(String name) {
			return advertRepository.minPriceForAgnecy(name);
		}
		
		public BigDecimal maxPriceForAgnecy(String name) {
			return advertRepository.maxPriceForAgnecy(name);
		}

		public BigDecimal avgPriceForAgnecy(String name) {
			return advertRepository.avgPriceForAgnecy(name);
		}
}

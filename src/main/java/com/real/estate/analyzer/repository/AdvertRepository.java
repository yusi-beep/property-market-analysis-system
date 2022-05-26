package com.real.estate.analyzer.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entities.Advert;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {
	
		//min, max, avg for neighbourhood
		@Query(value = "SELECT MIN(price) FROM Advert where neighbourhood = :id", nativeQuery = true)
		BigDecimal minPriceForNeighbourhood(@Param("id") Long id);
		
		@Query(value = "SELECT MAX(price) FROM Advert where neighbourhood = :id", nativeQuery = true)
		BigDecimal maxPriceForNeighbourhood(@Param("id") Long id);

		@Query(value = "SELECT CAST(AVG(price) AS DECIMAL(10,2)) FROM Advert where neighbourhood = :id", nativeQuery = true)
		BigDecimal avgPriceForNeighbourhood(@Param("id") Long id);
		
		//min, max, avg for agency
		@Query(value = "SELECT MIN(price) FROM Advert where agency = :id", nativeQuery = true)
		BigDecimal minPriceForAgnecy(@Param("id") Long id);
		
		@Query(value = "SELECT MAX(price) FROM Advert where agency = :id", nativeQuery = true)
		BigDecimal maxPriceForAgnecy(@Param("id") Long id);
		
		@Query(value = "SELECT CAST(AVG(price) AS DECIMAL(10,2)) FROM Advert where agency = :id", nativeQuery = true)
		BigDecimal avgPriceForAgnecy(@Param("id") Long id);
}

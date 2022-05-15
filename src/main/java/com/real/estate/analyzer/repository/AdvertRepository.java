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
		@Query(value = "SELECT min(price) FROM Advert where neighbourhood = :id", nativeQuery = true)
		BigDecimal minPriceForNeighbourhood(@Param("id") Long id);
		
		@Query(value = "SELECT max(price) FROM Advert where neighbourhood = :id", nativeQuery = true)
		BigDecimal maxPriceForNeighbourhood(@Param("id") Long id);

		@Query(value = "SELECT avg(price) FROM Advert where neighbourhood = :id", nativeQuery = true)
		BigDecimal avgPriceForNeighbourhood(@Param("id") Long id);
		
		//min, max, avg for agency
		@Query(value = "SELECT min(ad.price) FROM Advert ad " + 
		"JOIN RealEstateAgency a on a.id = ad.agency " +
		"where a.name = :name")
		BigDecimal minPriceForAgnecy(@Param("name") String name);
		
		@Query(value = "SELECT max(ad.price) FROM Advert ad " + 
		"JOIN RealEstateAgency a on a.id = ad.agency " +
		"where a.name = :name")
		BigDecimal maxPriceForAgnecy(@Param("name") String name);
		
		@Query(value = "SELECT avg(ad.price) FROM Advert ad " + 
		"JOIN RealEstateAgency a on a.id = ad.agency " +
		"where a.name = :name")
		BigDecimal avgPriceForAgnecy(@Param("name") String name);
}

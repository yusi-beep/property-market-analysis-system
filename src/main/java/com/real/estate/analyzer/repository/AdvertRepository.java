package com.real.estate.analyzer.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entities.Advert;
import com.real.estate.analyzer.entities.Neighbourhood;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {

		//min, max, avg for neighbourhood
		@Query(value = "SELECT min(ad.price) FROM Advert ad " + 
		"JOIN Neighbourhood n on n.id = ad.neighbourhood " +
		"where n.name = :name")
		public BigDecimal minPriceForNeighbourhood(@Param("name") String name);
		
		@Query(value = "SELECT max(ad.price) FROM Advert ad " + 
		"JOIN Neighbourhood n on n.id = ad.neighbourhood " +
		"where n.name = :name")
		public BigDecimal maxPriceForNeighbourhood(@Param("name") String name);
		
		@Query(value = "SELECT avg(ad.price) FROM Advert ad " + 
		"JOIN Neighbourhood n on n.id = ad.neighbourhood " +
		"where n.name = :name")
		public BigDecimal avgPriceForNeighbourhood(@Param("name") String name);
		
		//min, max, avg for agency
		@Query(value = "SELECT min(ad.price) FROM Advert ad " + 
		"JOIN RealEstateAgency a on a.id = ad.agency " +
		"where a.name = :name")
		public BigDecimal minPriceForAgnecy(@Param("name") String name);
		
		@Query(value = "SELECT max(ad.price) FROM Advert ad " + 
		"JOIN RealEstateAgency a on a.id = ad.agency " +
		"where a.name = :name")
		public BigDecimal maxPriceForAgnecy(@Param("name") String name);
		
		@Query(value = "SELECT avg(ad.price) FROM Advert ad " + 
		"JOIN RealEstateAgency a on a.id = ad.agency " +
		"where a.name = :name")
		public BigDecimal avgPriceForAgnecy(@Param("name") String name);
}

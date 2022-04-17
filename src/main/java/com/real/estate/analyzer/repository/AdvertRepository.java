package com.real.estate.analyzer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entities.Advert;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {

	//TODO min max avg - add  new param for city, naighbourhood and agency
	/*	@Query(value = "select MIN(ad.price)\n" +
				"from advert ad\n" +
				"join neighbourhood n on n.ID = ad.neighbourhood\n" +
				"where n.name = :name\n")
		Integer minPriceForNeighbourhood(@Param("name") String name);
		
		@Query(value = "select MAX(ad.price)\n" +
				"from advert ad\n" +
				"join neighbourhood n on n.ID = ad.neighbourhood\n" +
				"where n.name = :name\n")
		Integer maxPriceForNeighbourhood(@Param("name") String name);
		
		@Query(value = "select AVG(ad.price)\n" +
				"from advert ad\n" +
				"join neighbourhood n on n.ID = ad.neighbourhood\n" +
				"where n.name = :name\n")
		Double avgPriceForNeighbourhood(@Param("name") String name);	
*/}

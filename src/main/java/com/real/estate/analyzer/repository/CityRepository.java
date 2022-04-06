package com.real.estate.analyzer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entities.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
/*
	List<Advert> findAllByName(String city);
	//TODO 
	@Query(value = "select MIN(ad.price)\n" +
			"from advert ad\n" +
			"join neighborhood n on n.ID = ad.neighborhood_id\n" +
			"where n.city_id = (select c.id\n" +
			"from city\n" +
			"join neighborhood n on c.id = n.city_id\n" +
			"where c.name = :name)")
	Advert getMinPrice(@Param("name") String name);
	
	@Query(value = "select MAX(ad.price)\n" +
			"from advert ad\n" +
			"join neighborhood n on n.ID = ad.neighborhood_id\n" +
			"where n.city_id = (select c.id\n" +
			"from city\n" +
			"join neighborhood n on c.id = n.city_id\n" +
			"where c.name = :name)")
	Advert getMaxPrice(@Param("name") String name);
	
	@Query(value = "select AVG(ad.price)\n" +
			"from advert ad\n" +
			"join neighborhood n on n.ID = ad.neighborhood_id\n" +
			"where n.city_id = (select c.id\n" +
			"from city\n" +
			"join neighborhood n on c.id = n.city_id\n" +
			"where c.name = :name)")
	Advert getAvgPrice(@Param("name") String name);
*/
}

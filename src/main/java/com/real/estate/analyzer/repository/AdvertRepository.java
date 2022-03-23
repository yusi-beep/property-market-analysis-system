package com.real.estate.analyzer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entity.Advert;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {
	
	@Query(value = "select * \n" +
            "from advert \n" +
            "where address = :address \n" +
            "and city = :city \n" +
            "and floor = :floor \n" +
            "and square_footage = :squareFootage \n" +
            "and broker = :broker \n ",
            nativeQuery = true)
    Advert checkForDuplicates(
    		@Param("address") String address,
    		@Param("city") String city,
    		@Param("floor") Integer floor,
    		@Param("squareFootage") Integer squareFootage,
    		@Param("broker") String broker);
}

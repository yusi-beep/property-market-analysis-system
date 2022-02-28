package com.real.estate.analyzer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.real.estate.analyzer.entity.Advert;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {

	List<Advert> findByPrice(String price);
}

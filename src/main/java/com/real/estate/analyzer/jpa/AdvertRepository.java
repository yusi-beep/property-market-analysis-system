package com.real.estate.analyzer.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.real.estate.analyzer.entity.Advert;

public interface AdvertRepository extends CrudRepository<Advert, Long>{

	List<Advert> findByLastName(String price);

	Advert findById(long id);
}

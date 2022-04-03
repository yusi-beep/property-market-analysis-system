package com.real.estate.analyzer.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
	
@Data
@Entity
@NoArgsConstructor
public class City {
	
    @Id
    @GeneratedValue
    private Long id;
    
	@Column(unique = true)
	private String name;
	
	@OneToMany(mappedBy = "cityId", fetch = FetchType.EAGER)
    private List<Neighborhood> neighborhoods;
	
	public City(String name) {
		this.name = name;
	}
}

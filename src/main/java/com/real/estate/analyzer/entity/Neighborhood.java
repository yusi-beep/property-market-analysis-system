package com.real.estate.analyzer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Neighborhood {
	
    @Id
    @GeneratedValue
    private Long id;
    
	@Column(unique = true)
	private String name;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
	private City cityId;
	
	public Neighborhood(String name, City city) {
		this.name = name;
		this.cityId = city;
	}
}

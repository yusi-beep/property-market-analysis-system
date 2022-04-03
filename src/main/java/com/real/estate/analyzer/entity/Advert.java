package com.real.estate.analyzer.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Advert {
	
    @Id
    @GeneratedValue
    private Long id;
    
	@Enumerated(EnumType.STRING)
	@Column
	private RealEstateType typeEstate;
	
	@Column
	private Integer squareFootage;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "NEIGHBORHOOD_ID", referencedColumnName = "ID", nullable = false)
	private Neighborhood neighborhoodId;
	
	@Column
	private Integer price;
	
	@Column
	private Integer floor;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "AGENCY_ID", referencedColumnName = "ID", nullable = false)
	private RealEstateAgency agencyId;

	@Column(unique = true)
	private String url;
	
	@Column
	private LocalDateTime date;

	public Advert(RealEstateType typeEstate, Integer squareFootage, Neighborhood neighborhood,
			Integer price, Integer floor, RealEstateAgency agency, String url, LocalDateTime date) {
		
		this.typeEstate = typeEstate;
		this.squareFootage = squareFootage;
		this.neighborhoodId = neighborhood;
		this.price = price;
		this.floor = floor;
		this.agencyId = agency;
		this.url = url;
		this.date = date;
	}
}

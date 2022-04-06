package com.real.estate.analyzer.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.real.estate.analyzer.enums.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Advert {
	
    @Id
    @GeneratedValue
    private Long id;

	@Enumerated(EnumType.STRING)
	private RealEstateType typeEstate;

	private Integer squareFootage;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "neighbourhood")
	private Neighborhood neighbourhood;
	
	private Integer price;
	
	private Integer floor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "agency")
	private RealEstateAgency agency;

	@Column(unique = true)
	private String url;
	
	private LocalDateTime date;
}

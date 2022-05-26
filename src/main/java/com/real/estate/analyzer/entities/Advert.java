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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Enumerated(EnumType.STRING)
	private RealEstateType typeEstate;

	private Integer squareFootage;
	
	@ManyToOne
	@JoinColumn(name = "neighbourhood")
	private Neighbourhood neighbourhood;
	
	private Integer price;
	
	private Integer floor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "agency")
	private RealEstateAgency agency;

	@Column(unique = true)
	private String url;

	@Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private LocalDateTime date;
}

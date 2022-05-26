package com.real.estate.analyzer.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.real.estate.analyzer.entities.Neighbourhood;
import com.real.estate.analyzer.entities.RealEstateAgency;
import com.real.estate.analyzer.repository.AgencyRepository;
import com.real.estate.analyzer.repository.NeighbourhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
		
	@Autowired
	private NeighbourhoodRepository neighbourhoodRepository;
	
	@Autowired
	private AgencyRepository agencyRepository;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String cityList(Model theModel) {
		
		theModel.addAttribute("theDate", LocalDateTime.now());	
		List<Neighbourhood> neighbourhoodList = new ArrayList<>();
		neighbourhoodRepository.findAll().forEach(neighbourhoodList::add);
		theModel.addAttribute("neighbourhoodOptions", neighbourhoodList);
		
		List<RealEstateAgency> agencyList = new ArrayList<>();
		agencyRepository.findAll().forEach(agencyList::add);
		theModel.addAttribute("agencyOptions", agencyList);
		
		return "index";
	}
}

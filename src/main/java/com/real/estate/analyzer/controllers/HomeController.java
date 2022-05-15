package com.real.estate.analyzer.controllers;

import java.util.ArrayList;
import java.util.List;

import com.real.estate.analyzer.entities.Neighbourhood;
import com.real.estate.analyzer.repository.NeighbourhoodRepository;
import com.real.estate.analyzer.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
    private AdvertService advertServiceImpl;
	
	@Autowired
	private NeighbourhoodRepository neighbourhoodRepository;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String cityList(Model theModel) {
		List<Neighbourhood> neighbourhoodList = new ArrayList<>();
		neighbourhoodRepository.findAll().forEach(neighbourhoodList::add);

		theModel.addAttribute("neighbourhoodOptions", neighbourhoodList);
		return "homepage";
	}
	
	@RequestMapping(value = "/1", method = RequestMethod.GET)
	public String neighbourhoodAnalysis(
			@RequestParam(value="neighbourhoodIn") Long neighbourhoodId, Model theModel) {
		
		theModel.addAttribute("minPrice", advertServiceImpl.minPriceForNeighbourhood(neighbourhoodId));
		theModel.addAttribute("maxPrice", advertServiceImpl.maxPriceForNeighbourhood(neighbourhoodId));
		theModel.addAttribute("avgPrice", advertServiceImpl.avgPriceForNeighbourhood(neighbourhoodId));
		return "homepage";
	}
	
//	@RequestMapping(value="/2", method=RequestMethod.GET)
//	public String agencyAnalysis(@RequestParam(value="agencyIn", required = false) String agencyIn, Model theModel) {
//		theModel.addAttribute("agency", advertServiceImpl.minPriceForNeighbourhood(agencyIn));
//		return "homepage";
//	}

}

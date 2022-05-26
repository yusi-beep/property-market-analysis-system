package com.real.estate.analyzer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.real.estate.analyzer.service.AdvertService;

@Controller
public class NeighbourhoodController {
	
	@Autowired
    private AdvertService advertService;
	
	@RequestMapping(value = "/neighbourhood", method = RequestMethod.GET)
	public String neighbourhoodAnalysis(
			@RequestParam(value="neighbourhoodIn") Long neighbourhoodId, Model theModel) {
		
		theModel.addAttribute("minPrice", advertService.minPriceForNeighbourhood(neighbourhoodId));
		theModel.addAttribute("maxPrice", advertService.maxPriceForNeighbourhood(neighbourhoodId));
		theModel.addAttribute("avgPrice", advertService.avgPriceForNeighbourhood(neighbourhoodId));
		return "neighbourhoodpage";
	}

}

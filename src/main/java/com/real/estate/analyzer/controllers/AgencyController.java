package com.real.estate.analyzer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.real.estate.analyzer.service.AdvertService;

@Controller
public class AgencyController {
	
	@Autowired
    private AdvertService advertService;
	
	@RequestMapping(value="/agency", method=RequestMethod.GET)
	public String agencyAnalysis(@RequestParam(value="agencyIn") Long agencyIn, Model theModel) {
		
		theModel.addAttribute("minPrice", advertService.minPriceForAgnecy(agencyIn));
		theModel.addAttribute("maxPrice", advertService.maxPriceForAgnecy(agencyIn));
		theModel.addAttribute("avgPrice", advertService.avgPriceForAgnecy(agencyIn));
		return "agencypage";
	}

}

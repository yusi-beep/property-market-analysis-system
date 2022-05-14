package com.real.estate.analyzer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.real.estate.analyzer.service.AdvertServiceImpl;

@Controller
public class HomeController {
	
    private AdvertServiceImpl advertServiceImpl;
	
	@RequestMapping(value="/1", method=RequestMethod.GET)
	public String neighbourhoodAnalysis(Model theModel) {
		theModel.addAttribute("neighbourhood", advertServiceImpl.minPriceForNeighbourhood("Добруджански"));
		return "homepage";
	}
	
	@RequestMapping(value="/2", method=RequestMethod.GET)
	public String cityAnalysis(Model theModel) {
		theModel.addAttribute("city", advertServiceImpl.minPriceForNeighbourhood("Шумен"));
		return "homepage";
	}
	
	@RequestMapping(value="/3", method=RequestMethod.GET)
	public String agencyAnalysis(Model theModel) {
		theModel.addAttribute("agency", advertServiceImpl.minPriceForNeighbourhood("Имоти томов"));
		return "homepage";
	}
}

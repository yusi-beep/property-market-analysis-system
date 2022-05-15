package com.real.estate.analyzer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.real.estate.analyzer.repository.CityRepository;
import com.real.estate.analyzer.service.AdvertServiceImpl;

@Controller
public class HomeController {
	
	@Autowired
    private AdvertServiceImpl advertServiceImpl;
	
//	@Autowired
//	private CityRepository cityRepository;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String cityList(Model theModel) {
		List<String> cityList = new ArrayList<String>();
		cityList.add("Шумен");
		cityList.add("Варна");
		cityList.add("Бургас");
		cityList.add("София");
		
//		cityList = cityRepository.cityList();
		
		theModel.addAttribute("cityOptions", cityList);
		return "homepage";
	}
	
	@RequestMapping(value = "/1", method = RequestMethod.GET)
	public String neighbourhoodAnalysis(@RequestParam(value="cityIn", required = true) String cityIn,
			@RequestParam(value="neighbourhoodIn", required = false) String neighbourhoodIn, Model theModel) {
		
		theModel.addAttribute("city", advertServiceImpl.minPriceForNeighbourhood(cityIn));
		theModel.addAttribute("neighbourhood", advertServiceImpl.minPriceForNeighbourhood(neighbourhoodIn));
		return "homepage";
	}
	
	@RequestMapping(value="/2", method=RequestMethod.GET)
	public String agencyAnalysis(@RequestParam(value="agencyIn", required = false) String agencyIn, Model theModel) {
		theModel.addAttribute("agency", advertServiceImpl.minPriceForNeighbourhood(agencyIn));
		return "homepage";
	}

}

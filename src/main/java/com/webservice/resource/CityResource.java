package com.webservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.model.BetweenCities;
import com.webservice.model.City;
import com.webservice.services.CityService;

@RestController
@RequestMapping("/cidades")
public class CityResource {
	
	@Autowired
	private CityService cityService;
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<BetweenCities> listCities() {
		Iterable<BetweenCities> distances =  cityService.getDistanceBetweenCities();
		return distances;
	}
	
	@PostMapping
	public @ResponseBody City create(@RequestBody City city) {
		return cityService.create(city);
	}
}
	
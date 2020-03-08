package com.webservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservice.dao.CityDao;
import com.webservice.model.BetweenCities;
import com.webservice.model.City;
import com.webservice.model.utils.Distancing;

@Service
public class CityService {

	@Autowired
	private CityDao cityDao;
	private Distancing distancing = new Distancing();
	
	
	public List<BetweenCities> getDistanceBetweenCities() {
		List<City> cities = cityDao.findAll();
		return distancing.distance(cities, 0, cities.size() - 1);
	}
	
	public City create(City city) {
		return cityDao.insert(city);
	}
}

package services;

import java.util.ArrayList;
import java.util.List;

import dao.CityDao;
import models.BetweenCities;
import models.City;

public class CityService {

	CityDao cityDao = new CityDao();
	
	public List<BetweenCities> getDistanceBetweenCities() {
		List<City> cities = cityDao.findAll();
		List<BetweenCities> distances = new ArrayList<BetweenCities>();
		
		for(int i=0;i<cities.size()-1;i++) {
			for (int j=i+1;j<cities.size();j++) {
				BetweenCities betweenCities = new BetweenCities(cities.get(i), cities.get(j));
				distances.add(betweenCities);
			}
		}
		return distances;
	}
}

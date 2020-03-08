package com.webservice.model.utils;

import java.util.ArrayList;
import java.util.List;

import com.webservice.model.BetweenCities;
import com.webservice.model.City;

public class Distancing {
	
	private List<BetweenCities> distancesBetweenCities = new ArrayList<>();
	
	public void calculate(List<City> cities, int l, int m, int r) {
        int n1 = m - l + 1; 
        int n2 = r - m;
        List<City> citiesLeft = new ArrayList<City>();
        List<City> citiesRight = new ArrayList<City>();
        for (int i = 0;i < n1;++i) {
        	citiesLeft.add(cities.get(l+i));
        } 
        for (int i = 0;i < n2;++i) {
        	citiesRight.add(cities.get(m + 1 + i));
        }
        for (City one : citiesLeft) {
        	for (City two : citiesRight) {
        		distancesBetweenCities.add(new BetweenCities(one, two));
        	}
        }
	}
	
	public List<BetweenCities> distance(List<City> cities, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			distance(cities, l, m);
			distance(cities, m + 1, r);
			calculate(cities, l, m, r);
		}
		return distancesBetweenCities;
	}

}

package com.webservice.model;

public class BetweenCities {
	private City cityOne;
	private City cityTwo;
	private double distance;

	public BetweenCities() {
	}
	
	public BetweenCities(City one, City two) {
		this.cityOne = one;
		this.cityTwo = two;
		this.distance = distanceCalculate();
	}
	
	public City getCityOne() {
		return cityOne;
	}
	public void setCityOne(City cityOne) {
		this.cityOne = cityOne;
	}
	public City getCityTwo() {
		return cityTwo;
	}
	public void setCityTwo(City cityTwo) {
		this.cityTwo = cityTwo;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double distanceCalculate() {		
		double dist = (6371*Math.acos(Math.cos(Math.PI*(90 - (cityTwo.getLatitude()))/180)* Math.cos((90 - (cityOne.getLatitude()))* Math.PI/180)+ Math.sin((90 - (-cityTwo.getLatitude()))* Math.PI/180)* Math.sin((90 - (cityOne.getLatitude()))*Math.PI/180)* Math.cos((cityOne.getLongitude() - (cityTwo.getLongitude()))*Math.PI/180)));
		return dist;
	}
	
}

package com.garage.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.garage.be.Car;
import com.garage.singleton.GarageSingleton;

public class GarageStatusBO {
	
	public int garageOcupacyPercentage(){
		GarageSingleton.getInstance();
		Integer total = 0;
		for (Entry<Integer, List<Car>>  entry : GarageSingleton.getFloorLevelToParkingSpots().entrySet()) {
			total += entry.getValue().size();
		}
		
		return (total*100)/(GarageSingleton.FLOOR_LEVELS * GarageSingleton.CARS_PER_LEVEL);
	}
	
	public int avarageTotalTimeParked(){
		GarageSingleton.getInstance();
		int totalCars = 0;
		int totalHours = 0;
		
		for (Entry<Integer, List<Car>>  entry : GarageSingleton.getFloorLevelToParkingSpots().entrySet()) {
			totalCars += entry.getValue().size();
			for (Car car : entry.getValue()) {
				totalHours += car.getTicket().getTotalHours();
			}
		}
		
		int totalAvg = totalHours > 0 ? totalHours/totalCars:0;
		
		return totalAvg;
	}
	
	public Map<Integer,Integer> totalCarsPerLevel(){
		GarageSingleton.getInstance();
		Map<Integer,Integer> levelIdToTotalCars = new HashMap<Integer,Integer>();
		
		for (Entry<Integer, List<Car>>  entry : GarageSingleton.getFloorLevelToParkingSpots().entrySet()) {
			levelIdToTotalCars.put(entry.getKey(), entry.getValue().size());
		}
		
		return levelIdToTotalCars;
	}
	

}

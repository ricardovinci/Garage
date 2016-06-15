package com.garage.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.garage.be.Car;

public class GarageSingleton {

	public static final int FLOOR_LEVELS = 4;
	public static final int CARS_PER_LEVEL = 10;
	
	private static Map<Integer,List<Car>> floorLevelToParkingSpots = new HashMap<Integer,List<Car>>(); 
	
	 private static class GarageSingletonHolder {
	        static final GarageSingleton instance = new GarageSingleton();
	 }

	 
	private GarageSingleton() {
		createGarage();
	}
	
	//making this singleton class thread safe  (Initialization-on-demand holder idiom)
	public static GarageSingleton getInstance() {
		return GarageSingletonHolder.instance;
	 }
	
	//Creating a garage with 4 floors levels and 10 parking spots on each floor
	private void createGarage(){
		for (int i = 0; i < FLOOR_LEVELS; i++) {
			GarageSingleton.getFloorLevelToParkingSpots().put(i, new ArrayList<Car>(CARS_PER_LEVEL));
		}
	}

	public static Map<Integer,List<Car>> getFloorLevelToParkingSpots() {
		return floorLevelToParkingSpots;
	}

	
	
	
}

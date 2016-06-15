package com.garage.bo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.garage.be.Car;
import com.garage.be.Ticket;
import com.garage.singleton.GarageSingleton;

public class ParkingBO {

	public Ticket checkIn(){
		
		if(isGarageFull()) return null;
		
		Car car = new Car();
		car.setId(UUID.randomUUID().toString());
		
		//return lowestLevelFirst();
		return distributeCarAmongFloorLevelsAndReturnTicket(car);
		
	}
	
//	public void lowestLevelFirst(){};
	
	public Ticket checkOut(String carId){
		GarageSingleton.getInstance();
		//finding car
		for (Entry<Integer, List<Car>>  entry : GarageSingleton.getFloorLevelToParkingSpots().entrySet()) {
			for (int i = 0; i < entry.getValue().size(); i++) {
				if(entry.getValue().get(i).getId().equals(carId)){
					Car car = entry.getValue().get(i);
					Ticket ticket = car.getTicket();
					//removing car at parking spot i
					GarageSingleton.getFloorLevelToParkingSpots().get(ticket.getLevelId()).remove(i);
					//calculating total
					ticket.setTotalPrice(calculateTotalPrice(ticket.getTotalHours()));
					return ticket;
				}
			}
		}
		return null;
	}
	
	 
	
	private Ticket distributeCarAmongFloorLevelsAndReturnTicket(Car car){
		GarageSingleton.getInstance();
		Integer levelId = getLevelIdWithLessParkedCars(GarageSingleton.getFloorLevelToParkingSpots());
		
		Ticket ticket = createTicket(car, levelId);
		car.setTicket(ticket);
		
		GarageSingleton.getFloorLevelToParkingSpots().get(levelId).add(car);
		
		return ticket;
		
	}
	
	private boolean isGarageFull(){
		GarageSingleton.getInstance();
		
		for (Entry<Integer, List<Car>>  entry : GarageSingleton.getFloorLevelToParkingSpots().entrySet()) {
			if(entry.getValue().size() < GarageSingleton.CARS_PER_LEVEL) return false;
		}
		return true;
	}
	
	//getting floor id with less parked cars
	private Integer getLevelIdWithLessParkedCars(Map<Integer, List<Car>> floorLevelsToParkingSpots){
		Entry<Integer, List<Car>> min = null;
		for (Entry<Integer, List<Car>>  entry : floorLevelsToParkingSpots.entrySet()) {
		    if (min == null || min.getValue().size() > entry.getValue().size()) {
		        min = entry;
		    }
		}
		
		return min.getKey();
	}
	
	private Ticket createTicket(Car car, Integer levelId){
		Ticket ticket = new Ticket();
		ticket.setCar(car);
		ticket.setEntryDate(new Date());
		ticket.setLevelId(levelId);
		//generating hours between 1 to 10
		ticket.setTotalHours(1 + (int)(Math.random() * ((10 - 1) + 1)));
		
		return ticket;
	}
	
	private double calculateTotalPrice(int totalHours){
		return totalHours * 11.99;
	}

}

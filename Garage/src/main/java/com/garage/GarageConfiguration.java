package com.garage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.garage.bo.GarageStatusBO;
import com.garage.bo.ParkingBO;

@Configuration
public class GarageConfiguration {
	
	@Bean
	public GarageStatusBO garageStatusBO(){
		return new GarageStatusBO();
	}
	
	@Bean
	public ParkingBO parkingBO(){
		return new ParkingBO();
	}

}

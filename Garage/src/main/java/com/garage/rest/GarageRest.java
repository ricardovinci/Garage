package com.garage.rest;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.garage.be.Ticket;
import com.garage.bo.GarageStatusBO;
import com.garage.bo.ParkingBO;

@RestController
@RequestMapping("/rest/garage")
public class GarageRest {
	
	@Autowired
	private GarageStatusBO statusBO;
	
	@Autowired
	private ParkingBO parkingBO;

	
	@RequestMapping(value = "/getTotalOccupancy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseRestReponse getTotalOccupancy(){
		return new BaseRestReponse(statusBO.garageOcupacyPercentage());
	}	
	
	@RequestMapping(value = "/getAverageParkingTime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseRestReponse getAverageParkingTime(){
		return new BaseRestReponse(statusBO.avarageTotalTimeParked());
	}
	
	@RequestMapping(value = "/getTotalCarsPerLevel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getTotalCarsPerLevel(){
		//binding map to json
		String json = "[";
		Map<Integer, Integer> totalCarsPerLevel = statusBO.totalCarsPerLevel();
		
		for (Entry<Integer, Integer>  entry : totalCarsPerLevel.entrySet()) {
			json +="{\"levelId\":"+entry.getKey()+",\"total\":"+entry.getValue()+"},";
		}
		//removing last comma
		json = json.substring(0, json.length()-1);
		json +="]";
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkIn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketResponse checkIn(){
		Ticket ticket = parkingBO.checkIn();
		if(ticket == null) return new TicketResponse("Sorry our garage is full today =(");
		
		return new TicketResponse(ticket);
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkOut", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketResponse checkOut(@RequestBody BaseRestRequest request){
		Ticket ticket = parkingBO.checkOut(request.getCarId());
		if(ticket == null) return new TicketResponse("Sorry we could not find your ticket");
		
		return new TicketResponse(ticket);
	}
	
	
	
}

package com.garage.rest;

import java.text.Format;
import java.text.SimpleDateFormat;

import com.garage.be.Ticket;

public class TicketResponse {

	private String message;
	private String carId;
	private String entryDate;
	private int levelId;
	private int totalHours;
	private double totalPrice;
	
	public TicketResponse(String message){
		this.message = message;
	}

	public TicketResponse(Ticket ticket) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.levelId = ticket.getLevelId();
		this.carId = ticket.getCar().getId();
		this.entryDate = formatter.format(ticket.getEntryDate());
		this.totalHours = ticket.getTotalHours();
		this.totalPrice = ticket.getTotalPrice();
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	
}

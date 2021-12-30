package com.example.oop_project.models;

import java.time.LocalDate;

public class roadDeliver extends Order {

	public roadDeliver(){

	}

	public roadDeliver( String senderName, String receiverName, String receivedAddress, double distance, String item,
					   double weight, LocalDate date) {
		super(senderName, receiverName, receivedAddress, distance, item, weight, date);
		super.setCost(calCost());
		super.setType("Đường Bộ");
	}

	@Override
	public double calCost() {
		return super.getDistance() * 20000 + super.getWeight() * 5000;
	}
}

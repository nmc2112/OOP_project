package com.example.oop_project.models;

import java.time.LocalDate;

public class airDeliver extends Order {

	public airDeliver(){

	}

	public airDeliver(String senderName, String receiverName, String receivedAddress, double distance, String item,
					  double weight, LocalDate date) {
		super(senderName, receiverName, receivedAddress, distance, item, weight, date);
		super.setCost(calCost());
		super.setType("Hàng Không");

	}
	@Override
	public double calCost() {
		return super.getDistance() * 100000 + super.getWeight() * 100000 + 200000;
	}
}

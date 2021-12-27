package com.example.oop_project.models;

import java.time.LocalDate;

public class roadDeliver extends Order {

	public roadDeliver(){

	}

	public roadDeliver( String senderName, String receiverName, String receivedAddress, double distance, String item,
					   double weight, LocalDate date) {
		super(senderName, receiverName, receivedAddress, distance, item, weight, date);
		super.setCost(calculateCost("Đường Bộ"));
		super.setType("Đường Bộ");
	}


}

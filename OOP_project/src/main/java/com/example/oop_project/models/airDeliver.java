package com.example.oop_project.models;

import java.time.LocalDate;

public class airDeliver extends Order {

	public airDeliver(){

	}

	public airDeliver(String senderName, String receiverName, String receivedAddress, double distance, String item,
					  double weight, LocalDate date) {
		super(senderName, receiverName, receivedAddress, distance, item, weight, date);
		super.setCost(calculateCost("Hàng Không"));
		super.setType("Hàng Không");

	}

}

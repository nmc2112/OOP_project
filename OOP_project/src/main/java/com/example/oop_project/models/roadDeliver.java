package com.example.oop_project.models;

public class roadDeliver extends Order {

	public roadDeliver( String senderName, String receiverName, String receivedAddress, double distance, String item,
					   double weight) {
		super(senderName, receiverName, receivedAddress, distance, item, weight);
		super.setCost(calculateCost());
		super.setType("Đường Bộ");
	}

	@Override
	public double calculateCost() {
		return super.getDistance() * 20000 + super.getWeight() * 5000;
	}

}

package com.example.oop_project.models;

public class airDeliver extends Order {

	public airDeliver(String senderName, String receiverName, String receivedAddress, double distance, String item,
					  double weight) {
		super(senderName, receiverName, receivedAddress, distance, item, weight);
		super.setCost(calculateCost());
		super.setType("Hàng Không");

	}

	@Override
	public double calculateCost() {
		return super.getDistance() * 100000 + super.getWeight() * 100000 + 200000;
	}
}

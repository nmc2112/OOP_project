package com.example.oop_project.models;

public class roadDeliver extends Order {

	public roadDeliver( String senderName, String receiverName, String receivedAddress, double distance, String item,
					   double weight) {
		super(senderName, receiverName, receivedAddress, distance, item, weight);
		super.setCost(tinhCost());
		super.setType("Đường Bộ");
	}

	public double tinhCost(){
		double cost = super.getDistance() * 20000 + super.getWeight() * 5000;
		return cost;
	}
}

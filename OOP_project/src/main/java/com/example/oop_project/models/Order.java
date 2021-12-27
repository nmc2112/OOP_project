package com.example.oop_project.models;

public class Order {
	private String senderName;
	private String receiverName;
	private String receivedAddress;
	private double distance;
	private double cost = 0;
	private double weight;
	private String item;
	private String type;
	
	
	public Order(String senderName, String receiverName, String receivedAddress, double distance,String item,
			double weight) {
		super();
		this.item = item;
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.receivedAddress = receivedAddress;
		this.distance = distance;
		this.weight = weight;
	}
	
	
	public Order() {
		super();
	}


	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceivedAddress() {
		return receivedAddress;
	}
	public void setReceivedAddress(String receivedAddress) {
		this.receivedAddress = receivedAddress;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if(type == "Đường Bộ"){
			this.setCost(this.getDistance() * 20000 + this.getWeight() * 5000);
		}else {
			this.setCost(this.getDistance() * 100000 + this.getWeight() * 100000 + 200000);
		}
		this.type = type;
	}

	public double calculateCost(){
		return 0;
	}
}

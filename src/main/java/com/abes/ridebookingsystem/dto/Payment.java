package com.abes.ridebookingsystem.dto;

public class Payment {
	private String paymentId; // unique id for payment
	private String rideId; // to store unique ride that we can associate with payment
	private double amount;
	private String paymentMethod; // e.g., UPI, Card, Cash

	// to store new payment
	public Payment(String paymentId, String rideId, double amount, String paymentMethod) {
		this.paymentId = paymentId;
		this.rideId = rideId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
	}

	// getter methods
	public String getPaymentId() {
		return paymentId;
	}

	public String getRideId() {
		return rideId;
	}

	public double getAmount() {
		return amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	// setter method
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "Payment{" + "paymentId='" + paymentId + '\'' + ", rideId='" + rideId + '\'' + ", amount=" + amount
				+ ", method='" + paymentMethod + '\'' + '}';
	}
}

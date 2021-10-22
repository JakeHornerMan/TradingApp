package com.ab.tradingapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wallet {
	
	@Id
	private int userId;
	private int stockId;
	private double stockAmount;

	
	public Wallet() {}

	public Wallet(int userId, int stockId, double stockAmount) {
		super();
		
		this.userId = userId;
		this.stockId = stockId;
		this.stockAmount = stockAmount;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public double getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(double stockAmount) {
		this.stockAmount = stockAmount;
	}

//	@Override
//	public String toString() {
//		return "Wallet [userId=" + userId + ", stockId=" + stockId + ", stockAmount="
//				+ stockAmount + "]";
//	}
}

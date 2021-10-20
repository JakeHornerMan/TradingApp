package com.ab.tradingapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stocks {

	@Id
	private int stock_id;
	private String stock_name;
	private double stock_value;
	private double stock_fee;
	
	
	public Stocks () {}


	public Stocks(int stock_id, String stock_name, double stock_value, double stock_fee) {
		super();
		this.stock_id = stock_id;
		this.stock_name = stock_name;
		this.stock_value = stock_value;
		this.stock_fee = stock_fee;
	}


	public int getStock_id() {
		return stock_id;
	}


	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}


	public String getStock_name() {
		return stock_name;
	}


	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}


	public double getStock_value() {
		return stock_value;
	}


	public void setStock_value(double stock_value) {
		this.stock_value = stock_value;
	}


	public double getStock_fee() {
		return stock_fee;
	}


	public void setStock_fee(double stock_fee) {
		this.stock_fee = stock_fee;
	}


	@Override
	public String toString() {
		return "Stocks [stock_id=" + stock_id + ", stock_name=" + stock_name + ", stock_value=" + stock_value
				+ ", stock_fee=" + stock_fee + "]";
	}
	
	
	

}

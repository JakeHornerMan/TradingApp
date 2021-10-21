package com.ab.tradingapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Exchange {

	@Id
	private int exchange_id;
	private String exchange_code;
	private int stock_id;
	private String stock_name;
	private double stock_value;
	private double stock_fee;
	private double stock_amount;
	
	public Exchange() {}

	public Exchange(int exchange_id, String exchange_code, int stock_id, String stock_name, double stock_value,
			double stock_fee, double stock_amount) {
		super();
		this.exchange_id = exchange_id;
		this.exchange_code = exchange_code;
		this.stock_id = stock_id;
		this.stock_name = stock_name;
		this.stock_value = stock_value;
		this.stock_fee = stock_fee;
		this.stock_amount = stock_amount;
	}

	public int getExchange_id() {
		return exchange_id;
	}

	public void setExchange_id(int exchange_id) {
		this.exchange_id = exchange_id;
	}

	public String getExchange_code() {
		return exchange_code;
	}

	public void setExchange_code(String exchange_code) {
		this.exchange_code = exchange_code;
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

	public double getStock_amount() {
		return stock_amount;
	}

	public void setStock_amount(double stock_amount) {
		this.stock_amount = stock_amount;
	}

	@Override
	public String toString() {
		return "Exchange [exchange_id=" + exchange_id + ", exchange_code=" + exchange_code + ", stock_id=" + stock_id
				+ ", stock_name=" + stock_name + ", stock_value=" + stock_value + ", stock_fee=" + stock_fee
				+ ", stock_amount=" + stock_amount + "]";
	}
	
}

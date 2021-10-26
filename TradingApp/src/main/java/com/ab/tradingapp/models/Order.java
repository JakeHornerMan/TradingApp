package com.ab.tradingapp.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order {

	public Order() {}
	
	@Id
    @GeneratedValue
	private int order_id;
	private int user_id;
	private int stock_id;
	private String exchange_code;
	private String type;
	private double transaction_amount;
	private double transaction_cost;
	private LocalDateTime dateTime;
	
	public Order(int order_id, int user_id, int stock_id, String exchange_code, String type, double transaction_amount,
			double transaction_cost, LocalDateTime dateTime) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.stock_id = stock_id;
		this.exchange_code = exchange_code;
		this.type = type;
		this.transaction_amount = transaction_amount;
		this.transaction_cost = transaction_cost;
		this.dateTime = dateTime;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	public String getExchange_code() {
		return exchange_code;
	}

	public void setExchange_code(String exchange_code) {
		this.exchange_code = exchange_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public double getTransaction_cost() {
		return transaction_cost;
	}

	public void setTransaction_cost(double transaction_cost) {
		this.transaction_cost = transaction_cost;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", user_id=" + user_id + ", stock_id=" + stock_id + ", exchange_code="
				+ exchange_code + ", type=" + type + ", transaction_amount=" + transaction_amount + ", transaction_cost="
				+ transaction_cost + ", dateTime=" + dateTime + "]";
	}
	
}

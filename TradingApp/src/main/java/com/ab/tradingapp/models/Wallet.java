package com.ab.tradingapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wallet {
	
	@Id
	private int wallet_id;
	private int user_Id;
	private int stock_Id;
	private double stock_Amount;
	private String stock_name;
	
	public Wallet() {}

	public Wallet(int wallet_id, int user_Id, int stock_Id, double stock_Amount, String stock_name) {
		super();
		this.wallet_id = wallet_id;
		this.user_Id = user_Id;
		this.stock_Id = stock_Id;
		this.stock_Amount = stock_Amount;
		this.stock_name = stock_name;
	}


	public int getWallet_id() {
		return wallet_id;
	}


	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}


	public int getUser_Id() {
		return user_Id;
	}


	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}


	public int getStock_Id() {
		return stock_Id;
	}


	public void setStock_Id(int stock_Id) {
		this.stock_Id = stock_Id;
	}


	public double getStock_Amount() {
		return stock_Amount;
	}


	public void setStock_Amount(double stock_Amount) {
		this.stock_Amount = stock_Amount;
	}

	public String getStock_name() {
		return stock_name;
	}

	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}

	@Override
	public String toString() {
		return "Wallet [wallet_id=" + wallet_id + ", user_Id=" + user_Id + ", stock_Id=" + stock_Id + ", stock_Amount="
				+ stock_Amount + ", stock_name:"+stock_name+"]";
	}
	
}

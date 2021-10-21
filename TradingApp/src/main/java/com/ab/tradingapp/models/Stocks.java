package com.ab.tradingapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stocks {
	
	@Id
	private int stock_id;
	private String stock_name; 
	private boolean stock_bse;
	private boolean stock_nse;
	private boolean stock_tyo;
	
	public Stocks() {}

	public Stocks(int stock_id, String stock_name, boolean stock_bse, boolean stock_nse, boolean stock_tyo) {
		super();
		this.stock_id = stock_id;
		this.stock_name = stock_name;
		this.stock_bse = stock_bse;
		this.stock_nse = stock_nse;
		this.stock_tyo = stock_tyo;
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

	public boolean isStock_bse() {
		return stock_bse;
	}

	public void setStock_bse(boolean stock_bse) {
		this.stock_bse = stock_bse;
	}

	public boolean isStock_nse() {
		return stock_nse;
	}

	public void setStock_nse(boolean stock_nse) {
		this.stock_nse = stock_nse;
	}

	public boolean isStock_tyo() {
		return stock_tyo;
	}

	public void setStock_tyo(boolean stock_tyo) {
		this.stock_tyo = stock_tyo;
	}

//	@Override
//	public String toString() {
//		return "Stocks [stock_id=" + stock_id + ", stcok_name=" + stock_name + ", stock_bse=" + stock_bse
//				+ ", stock_nse=" + stock_nse + ", stock_tyo=" + stock_tyo + "]";
//	}
	
	
	
	

//	CREATE TABLE `Stocks` (
//			`stock_id` INT NOT NULL AUTO_INCREMENT,
//			`stock_name` varchar(255) NOT NULL,
//			`stock_bse` BOOLEAN NOT NULL DEFAULT false,
//			`stock_nse` BOOLEAN NOT NULL DEFAULT false,
//			`stock_tyo` BOOLEAN NOT NULL DEFAULT false,
//			PRIMARY KEY (`stock_id`)
//		);
	

}

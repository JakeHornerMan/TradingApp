package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int walletId;
	private int userId;
	private int stockId;
	private double stockAmount;

	
	public Wallet() {}

	public Wallet(int walletId, int userId, int stockId, double stockAmount) {
		super();
		this.walletId = walletId;
		this.userId = userId;
		this.stockId = stockId;
		this.stockAmount = stockAmount;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
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

	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", userId=" + userId + ", stockId=" + stockId + ", stockAmount="
				+ stockAmount + "]";
	}
}

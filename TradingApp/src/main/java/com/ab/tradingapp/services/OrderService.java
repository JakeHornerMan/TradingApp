package com.ab.tradingapp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.tradingapp.models.Order;
import com.ab.tradingapp.models.Wallet;
import com.ab.tradingapp.repos.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo repo;
	
	List<Order> cart = new ArrayList<Order>();
	
	public void addToCart(Order o) {
		cart.add(o);
	}
	
	public List<Order> getCart() {
		return cart;
	}
	
	public void purchaseCart() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		for(Order o : cart) {
			o.setDateTime(now);
			save(o);
		}
	}
	
	public void save(Order o) {
	     //repo.save(o);
	}

}

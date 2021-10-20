package com.ab.tradingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.tradingapp.models.Order;
import com.ab.tradingapp.repos.OrderRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo or;
	
	public Order saveOrder(Order order) {
		return or.save(order);
	}
}

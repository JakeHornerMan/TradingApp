package com.ab.tradingapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ab.tradingapp.models.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{

	
}

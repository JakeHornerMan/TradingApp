package com.ab.tradingapp.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.ab.tradingapp.models.Order;
import com.ab.tradingapp.repos.OrderRepo;

@Service
@Configurable
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
	
	public void deleteCartItem(int orderId) {
		
		Iterator<Order> ordersIt = cart.iterator();
		
		while(ordersIt.hasNext()) {
			
			Order o = ordersIt.next();
			
			if(orderId == (o.getOrder_id())) {
				ordersIt.remove();
			}
		}
		
	}
	
	public void replaceOrder (int orderId) {
		Iterator<Order> orders = cart.iterator();
		
		while(orders.hasNext()) {
			Order o = orders.next();
			
			if(orderId == (o.getOrder_id())) {
				cart.set(o.getOrder_id()-1, o); 
			}
		}
	}
	
	
	public void purchaseCart() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		for(Order o : cart) {
			o.setDateTime(now);
			o.setOrder_id(0);
			//save(o);
			saveMan(o);
			
		}
	}
	
	public void save(Order o) {
	     repo.save(o);
	}
	
	public void saveMan(Order o) {
		repo.insertItemsIntoOrders(o.getUser_id(), o.getStock_id(), o.getExchange_code(), o.getType(), o.getTransaction_amount(), o.getTransaction_cost(),o.getDateTime(),o.getStock_name());
	}

	public void clearCart() {
		cart.clear();
	}
	
	/*public Integer createOrder (int user_id, int stock_id, String exchange_code, String type, double transaction_amount, double transaction_cost, LocalDateTime date ) {
		return repo.insertItemsIntoOrders(user_id, stock_id, exchange_code,  type, transaction_amount, transaction_cost, date);
	}*/
	
	public List<Order> viewOrderHistory (int user_id) {
		return repo.findByuser_id(user_id);
}
	
}

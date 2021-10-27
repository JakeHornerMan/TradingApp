package com.ab.tradingapp.repos;



import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.tradingapp.models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO Orders (user_id, stock_id, exchange_code, type, transaction_amount,transaction_cost,order_date) VALUES (:user_id, :stock_id, :exchange_code, :type, :transaction_amount, :transaction_cost, :order_date)", nativeQuery = true)
	public Integer insertItemsIntoOrders(@Param("user_id") int user_id, @Param("stock_id") int stock_id, @Param("exchange_code") String exchange_code,  
			@Param("type") String type, @Param("transaction_amount") double transaction_amount, @Param("transaction_cost") double transaction_cost, 
			@Param("order_date") LocalDateTime order_date);
	


	@Query(value="SELECT * from Orders o where o.user_id = :user_id",nativeQuery=true)
	List<Order> findByuser_id(@Param("user_id") int user_id);

	
}
 
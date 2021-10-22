package com.ab.tradingapp.repos;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ab.tradingapp.models.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

	/*@Modifying
	@Transactional
	@Query("INSERT INTO Orders (user_id, stock_id, exchange_code, type, transaction_amount,transaction_cost,order_date) "
			+ "VALUES (uid, sid, excode, ty, tranam, tranco, ordate)")
	Integer insertItemsIntoOrders(@Param("uid") int user_id, @Param("sid") int stock_id,  @Param("excode") int exchange_code,
			@Param("ty") String type, @Param("tranam") double transaction_amount, @Param("tranco") double transaction_cost, 
			@Param("ordate") Date order_date);
	*/
}

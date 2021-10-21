package com.ab.tradingapp.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.tradingapp.models.Exchange;

@Repository
public interface ExchangeRepo extends JpaRepository<Exchange,Integer> {

	@Query("SELECT e FROM Exchange e WHERE e.exchange_code =: exchangeCode AND e.stock_id =: stockId")
	Exchange viewExangesFromExchangeCodeAndStockId(
			@Param("exchangeCode") String exchangeCode, @Param("stockId") int stockId);
	
	@Query("SELECT e FROM Exchange e WHERE e.stock_id =: stockId")
	List<Exchange> viewAllExangesFromStockId(@Param("stockId") int stockId);
	
}

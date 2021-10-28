package com.ab.tradingapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ab.tradingapp.models.Stocks;
import com.ab.tradingapp.models.User;


@Repository
public interface StockRespo extends JpaRepository<Stocks, Integer> {
	
	@Query("SELECT s FROM Stocks s")
	List<Stocks> viewStock ();
	
	@Query("SELECT s FROM Stocks s WHERE s.stock_id = ?1")
    Stocks findByID(int id);
}

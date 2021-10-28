package com.ab.tradingapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.ab.tradingapp.models.Stocks;
import com.ab.tradingapp.models.User;
import com.ab.tradingapp.repos.StockRespo;

@Service
public class StockService {
	
	@Autowired
	StockRespo stockrespo;

//	public List<Stocks> viewStocks () {
//		return stockrespo.viewStock();
//	}
	
	public List<Stocks> listAll() {
        return stockrespo.findAll();
    }

	public Stocks returnStockById(int id) {
		return stockrespo.findByID(id);
	}
}

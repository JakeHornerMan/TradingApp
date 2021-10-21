package com.ab.tradingapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ab.tradingapp.models.Exchange;
import com.ab.tradingapp.repos.ExchangeRepo;

@Service
public class ExchangeService {

	@Autowired
	ExchangeRepo exchangerepo;
	
	public Exchange FindExchageInfo(String exchangeCode, int stockId) {
		return exchangerepo.viewExangesFromExchangeCodeAndStockId(exchangeCode, stockId);
	}
	
	public List<Exchange> FindAllExchagesForStock(int stockId) {
		return exchangerepo.viewAllExangesFromStockId(stockId);
	}
	
}

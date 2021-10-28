package com.ab.tradingapp.tradingapp;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.ab.tradingapp.models.Stocks;
import com.ab.tradingapp.services.StockService;

@SpringBootTest
public class StockServiceTests {
	
	@Autowired 
	StockService stockService;
	
	@Test //listAll
	void testStockServiceListAll() {
		List<Stocks>stocks = new ArrayList();
		stocks.add(new Stocks(2, "Stock name", true, true, false));
		List<Stocks> actualResult = stockService.listAll();
		assertEquals(actualResult, stocks);
	}

}


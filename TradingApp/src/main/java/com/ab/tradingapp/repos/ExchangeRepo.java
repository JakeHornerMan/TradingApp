package com.ab.tradingapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ab.tradingapp.models.Exchange;

@Repository
public interface ExchangeRepo {

	@Query("SELECT Exchange e FROM Exchange e")
	List<Exchange> viewExanges(Exchange e);
}

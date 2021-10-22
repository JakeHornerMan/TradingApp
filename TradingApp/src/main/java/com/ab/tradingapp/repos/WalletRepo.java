package com.ab.tradingapp.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ab.tradingapp.models.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer>{
	
	

}

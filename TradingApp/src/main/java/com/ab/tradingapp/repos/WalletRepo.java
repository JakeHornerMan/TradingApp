package com.ab.tradingapp.repos;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ab.tradingapp.models.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer>{
	
	/*
	@Transactional
	@Query("SELECT user_id, stock_id, stock_amount FROM Wallet WHERE user_id = :userid")
	List<Wallet> getWalletItemsByUserId(@Param("userid") int user_id);
	 */

}

package com.ab.tradingapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;


import com.ab.tradingapp.models.Wallet;
import com.ab.tradingapp.repos.WalletRepo;

@Service
@Configurable
public class WalletService {
	
	@Autowired
	private WalletRepo repo;
	
	public List<Wallet> listAll(){
		return repo.findAll();
	}
	
	public void save(Wallet wallet) {
	     repo.save(wallet);
	}

	public Wallet get(int user_id) {
	     return repo.findById(user_id).get();
	}

	public void delete(int userID) {
	     repo.deleteById(userID);
	}
	
	/*public List<Wallet> listAllByID(int user_id) {
		return repo.getWalletItemsByUserId(user_id);
	}*/
}

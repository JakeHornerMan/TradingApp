package com.ab.tradingapp.tradingapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.ab.tradingapp.models.Wallet;
import com.ab.tradingapp.services.WalletService;

@SpringBootTest
public class WalletServiceTests {

	
	@SpringBootTest
	class WalletServiceTest{
		
		
		@Autowired 
		WalletService walletService;
		
		@Test //listAll
		void testWalletServiceListAll() {
			List<Wallet> wallets = new ArrayList();
			wallets.add(new Wallet(2, 2, 2, 2.22, "name"));
			List<Wallet> actualResult = walletService.listAll();
			assertEquals(actualResult, wallets);
		}
		
		
//		@Test //save
//		void testWalletServiceSave() {
//			Wallet wallet = new Wallet(2, 2, 2, 2.22, "name");
//			Wallet actualResult = walletService.save(wallet);
//			assertNotNull(actualResult);
//		}
//		
		@Test //get
		void testWalletSericeGet() {
			int walletId = 1;
			Wallet actualResult = walletService.get(walletId);
			assertNotNull(actualResult);
		}
		
//		@Test //delete
//		void testWalletServiceDelete() {
//			int walletId = 1;
//			assertEquals(walletService.delete(walletId), 1);
//		}
	}

}

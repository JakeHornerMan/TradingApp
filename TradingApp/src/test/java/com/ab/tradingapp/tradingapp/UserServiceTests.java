package com.ab.tradingapp.tradingapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.ab.tradingapp.models.User;
import com.ab.tradingapp.services.UserService;

@SpringBootTest
class UserServiceTests {
	
	@Autowired
	UserService userService;
	
	@Test //listAll
	void testUserServiceListAll() {
		List<User> users = new ArrayList();
		users.add(new User(10,"k@gmail.com","1222",0.0));
		List<User> actualResult = userService.listAll();
		assertEquals(actualResult, users);
	}
	
//	@Test //save
//	void testUserServiceSave() {
//		User user = new User(10,"k@gmail.com","1222",0.0); 
//		User actualResult = userService.save(user); 
//		assertNotNull(actualResult); 
//	}

	@Test //get
	void testUserServiceGet() {
		int userId = 1;
		User actualResult = userService.get(userId);
		assertNotNull(actualResult);
	}
	
//	@Test //delete
//	void testUserServiceDelete() {
//		int userId = 1;
//		assertEquals(userService.delete(userId),1);
//	}
}

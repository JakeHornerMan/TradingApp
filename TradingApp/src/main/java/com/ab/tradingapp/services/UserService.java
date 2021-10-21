package com.ab.tradingapp.services;

import com.ab.tradingapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import com.ab.tradingapp.repos.UserRepo;

import java.util.List;

@Service
@Configurable
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<User> listAll() {
        return repo.findAll();
    }

    public void save(User user) {
        user.setUser_balance(1000.00);
        repo.save(user);
    }

    public User get(int user_id) {
        return repo.findById(user_id).get();
    }

    public void delete(int userID) {
        repo.deleteById(userID);
    }



}


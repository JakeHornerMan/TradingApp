package com.ab.tradingapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String user_email;
    private String user_password;
    private double user_balance;

    public User(int user_id, String user_email, String user_password, double user_balance) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_balance = user_balance;
    }

    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public double getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(double user_balance) {
        this.user_balance = user_balance;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "user_id=" + user_id +
//                ", user_email='" + user_email + '\'' +
//                ", user_password='" + user_password + '\'' +
//                ", wallet_id=" + user_balance +
//                '}';
//    }
}

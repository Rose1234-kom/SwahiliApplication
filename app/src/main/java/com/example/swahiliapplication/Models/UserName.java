package com.example.swahiliapplication.Models;

public class UserName {
    String userId;

    public UserName(String userId) {
        this.userId = userId;
    }
    public UserName(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

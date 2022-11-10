package com.example.swahiliapplication.Models;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;

public class UserInformation {
    String userId,userName, emailAddress, country, dailyGoal;
    ArrayList<String> learningPurposes=new ArrayList<>();

    @ServerTimestamp
    FieldValue timeStamp;

    public UserInformation() {
    }

    public UserInformation(String userId, String userName, String emailAddress, String country, String dailyGoal, ArrayList<String> learningPurposes) {
        this.userId = userId;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.country = country;
        this.dailyGoal = dailyGoal;
        this.learningPurposes = learningPurposes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(String dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public ArrayList<String> getLearningPurposes() {
        return learningPurposes;
    }

    public void setLearningPurposes(ArrayList<String> learningPurposes) {
        this.learningPurposes = learningPurposes;
    }
}

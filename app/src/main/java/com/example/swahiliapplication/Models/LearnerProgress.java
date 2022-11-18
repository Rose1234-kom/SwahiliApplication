package com.example.swahiliapplication.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LearnerProgress {
    private String userId, userLevel, levelId;
    private double userScore;

    public LearnerProgress() {
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public LearnerProgress(String userId, String userLevel, String levelId, double userScore) {
        this.userId = userId;
        this.userLevel = userLevel;
        this.levelId=levelId;
        this.userScore = userScore;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public double getUserScore() {
        return userScore;
    }

    public void setUserScore(double userScore) {
        this.userScore = userScore;
    }

}

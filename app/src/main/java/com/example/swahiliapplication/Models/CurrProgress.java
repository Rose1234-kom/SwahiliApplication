package com.example.swahiliapplication.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CurrProgress {
    private String userId;
    private ArrayList<String> level, levelContent;
    private Map<String,ArrayList<Integer>> answeredQuestions=new HashMap<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<String> getLevel() {
        return level;
    }

    public void setLevel(ArrayList<String> level) {
        this.level = level;
    }

    public ArrayList<String> getLevelContent() {
        return levelContent;
    }

    public void setLevelContent(ArrayList<String> levelContent) {
        this.levelContent = levelContent;
    }

    public Map<String,ArrayList<Integer>> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(Map<String,ArrayList<Integer>> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    public CurrProgress() {
    }

    public CurrProgress(String userId, ArrayList<String> level, ArrayList<String> levelContent, Map<String,ArrayList<Integer>> answeredQuestions) {
        this.userId = userId;
        this.level = level;
        this.levelContent = levelContent;
        this.answeredQuestions = answeredQuestions;
    }
}

package com.example.swahiliapplication;

import java.util.ArrayList;

public class FixedDataSource {
    private ArrayList<String> sentenceToTranslateList = new ArrayList<>();
    private ArrayList<String> sentenceTranslatedList = new ArrayList<>();
    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<String> answerToQuestionList = new ArrayList<>();
    private ArrayList<String[]> wordSetConfuse = new ArrayList<>();
    private ArrayList<String[]> wordSetCorrect = new ArrayList<>();

    public ArrayList<String> getSentenceToTranslateList() {
        return sentenceToTranslateList;
    }

    public void setSentenceToTranslateList(ArrayList<String> sentenceToTranslateList) {
        this.sentenceToTranslateList = sentenceToTranslateList;
    }

    public ArrayList<String> getSentenceTranslatedList() {
        return sentenceTranslatedList;
    }

    public void setSentenceTranslatedList(ArrayList<String> sentenceTranslatedList) {
        this.sentenceTranslatedList = sentenceTranslatedList;
    }

    public ArrayList<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<String> questionList) {
        this.questionList = questionList;
    }

    public ArrayList<String> getAnswerToQuestionList() {
        return answerToQuestionList;
    }

    public void setAnswerToQuestionList(ArrayList<String> answerToQuestionList) {
        this.answerToQuestionList = answerToQuestionList;
    }

    public ArrayList<String[]> getWordSetConfuse() {
        return wordSetConfuse;
    }

    public void setWordSetConfuse(ArrayList<String[]> wordSetConfuse) {
        this.wordSetConfuse = wordSetConfuse;
    }

    public ArrayList<String[]> getWordSetCorrect() {
        return wordSetCorrect;
    }

    public void setWordSetCorrect(ArrayList<String[]> wordSetCorrect) {
        this.wordSetCorrect = wordSetCorrect;
    }

    //Setup fixed source of data. In this case, a few sentences in one array list and their expected answers in another

    public void setupSentencesToTranslate() {
        sentenceToTranslateList.add(0, "Habari Yako");
        sentenceToTranslateList.add(1, "Jina lako ni nani?");
        sentenceToTranslateList.add(2, "Unaishi wapi?");
        sentenceToTranslateList.add(3, "Asante sana");
        sentenceToTranslateList.add(4, "Samahani");

        sentenceTranslatedList.add(0, "How are you?");
        sentenceTranslatedList.add(1, "What is your name?");
        sentenceTranslatedList.add(2, "Where do you live?");
        sentenceTranslatedList.add(3, "Thank you very much");
        sentenceTranslatedList.add(4, "Excuse me");

    }

    public void setupWordsToConfuse() {
        wordSetConfuse.add(0, new String[]{"Mine", "Is", "Why"});
        wordSetConfuse.add(1, new String[]{"Yours", "Are"});
        wordSetConfuse.add(2, new String[]{"Leave", "Does"});
        wordSetConfuse.add(3, new String[]{"Too", "Thanks"});
        wordSetConfuse.add(4, new String[]{"Excuses", "You"});
    }

    public void setupCorrectWords(){
        wordSetCorrect.add(0,new String[]{"are", "How", "you?"});
        wordSetCorrect.add(1,new String[]{"name?", "is", "What","your"});
        wordSetCorrect.add(2,new String[]{"do", "live?", "Where","you"});
        wordSetCorrect.add(3,new String[]{"much", "Thank", "very","you"});
        wordSetCorrect.add(4,new String[]{"me", "Excuse"});
    }
}

package com.example.swahiliapplication;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;

public class FixedDataSource {
    private ArrayList<String> sentenceToTranslateList = new ArrayList<>();
    private ArrayList<String> sentenceTranslatedList = new ArrayList<>();
    private ArrayList<String[]> wordSetConfuse = new ArrayList<>();
    private ArrayList<String[]> wordSetCorrect = new ArrayList<>();
    private ArrayList<String> greetingsToTranslateList=new ArrayList<>();
    private ArrayList<String> greetingsTranslatedList=new ArrayList<>();
    private ArrayList<String> greetingsWordCorrectList=new ArrayList<>();
    private ArrayList<String> greetingsWordConfuseList=new ArrayList<>();

//    private ArrayList<String> numbersToTranslateList=new ArrayList<>();
    private ArrayList<String> numbersTranslatedList=new ArrayList<>();
    private ArrayList<String> numbersWordCorrectList=new ArrayList<>();
    private ArrayList<String> numbersWordConfuseList=new ArrayList<>();

    private ArrayList<String> coloursToTranslateList=new ArrayList<>();
    private ArrayList<String> coloursTranslatedList=new ArrayList<>();
    private ArrayList<String> coloursWordCorrectList=new ArrayList<>();
    private ArrayList<String> coloursWordConfuseList=new ArrayList<>();




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

    public void setupGreetingsToTranslate(){
        greetingsToTranslateList.add(0,"");
        greetingsToTranslateList.add(1,"");
        greetingsToTranslateList.add(2,"");
        greetingsToTranslateList.add(3,"");
        greetingsToTranslateList.add(4,"");

        greetingsTranslatedList.add(0,"");
        greetingsTranslatedList.add(1, "");
        greetingsTranslatedList.add(2,"");
        greetingsTranslatedList.add(3,"");
        greetingsTranslatedList.add(4,"");

        greetingsWordCorrectList.add(0,"");
        greetingsWordCorrectList.add(1,"");
        greetingsWordCorrectList.add(2,"");
        greetingsWordCorrectList.add(3,"");
        greetingsWordCorrectList.add(4,"");

        greetingsWordConfuseList.add(0,"");
        greetingsWordConfuseList.add(1,"");
        greetingsWordConfuseList.add(2,"");
        greetingsWordConfuseList.add(3,"");
        greetingsWordConfuseList.add(4,"");
    }

    public void setupNumbersToTranslate(){
        /*greetingsToTranslateList.add(0,"");
        greetingsToTranslateList.add(1,"");
        greetingsToTranslateList.add(2,"");
        greetingsToTranslateList.add(3,"");
        greetingsToTranslateList.add(4,"");*/

        numbersTranslatedList.add(0,"");
        numbersTranslatedList.add(1, "");
        numbersTranslatedList.add(2,"");
        numbersTranslatedList.add(3,"");
        numbersTranslatedList.add(4,"");

        numbersWordCorrectList.add(0,"");
        numbersWordCorrectList.add(1,"");
        numbersWordCorrectList.add(2,"");
        numbersWordCorrectList.add(3,"");
        numbersWordCorrectList.add(4,"");

        numbersWordConfuseList.add(0,"");
        numbersWordConfuseList.add(1,"");
        numbersWordConfuseList.add(2,"");
        numbersWordConfuseList.add(3,"");
        numbersWordConfuseList.add(4,"");
    }

    public void setupColoursToTranslate(){
        coloursToTranslateList.add(0,"");
        coloursToTranslateList.add(1,"");
        coloursToTranslateList.add(2,"");
        coloursToTranslateList.add(3,"");
        coloursToTranslateList.add(4,"");

        coloursTranslatedList.add(0,"");
        coloursTranslatedList.add(1, "");
        coloursTranslatedList.add(2,"");
        coloursTranslatedList.add(3,"");
        coloursTranslatedList.add(4,"");

        coloursWordCorrectList.add(0,"");
        coloursWordCorrectList.add(1,"");
        coloursWordCorrectList.add(2,"");
        coloursWordCorrectList.add(3,"");
        coloursWordCorrectList.add(4,"");

        coloursWordConfuseList.add(0,"");
        coloursWordConfuseList.add(1,"");
        coloursWordConfuseList.add(2,"");
        coloursWordConfuseList.add(3,"");
        coloursWordConfuseList.add(4,"");
    }

    public void setupWordsToConfuse() {
        String[] wordConfuseOne={"Mine","Is","Why"};
        String[] wordConfuseTwo={"Yours","Are"};
        String[] wordConfuseThree= {"Leave","Does"};
        String[] wordConfuseFour={"Too","Thanks"};
        String[] wordConfuseFive={"Excuses","You"};

        wordSetConfuse.add(0, wordConfuseOne);
        wordSetConfuse.add(1, wordConfuseTwo);
        wordSetConfuse.add(2, wordConfuseThree);
        wordSetConfuse.add(3, wordConfuseFour);
        wordSetConfuse.add(4, wordConfuseFive);
    }

    public void setupCorrectWords(){
        wordSetCorrect.add(0,new String[]{"are", "How", "you?"});
        wordSetCorrect.add(1,new String[]{"name?", "is", "What","your"});
        wordSetCorrect.add(2,new String[]{"do", "live?", "Where","you"});
        wordSetCorrect.add(3,new String[]{"much", "Thank", "very","you"});
        wordSetCorrect.add(4,new String[]{"me", "Excuse"});
    }
}

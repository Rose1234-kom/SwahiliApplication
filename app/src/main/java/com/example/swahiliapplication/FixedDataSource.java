package com.example.swahiliapplication;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;

public class FixedDataSource {

    //Array Lists to store strings of content to translate and the result of translation
    private ArrayList<String> sentenceToTranslateList = new ArrayList<>();
    private ArrayList<Boolean> sentencesAnsweredCorrectList=new ArrayList<>();
    private ArrayList<Boolean> sentencesAnsweredWrongList=new ArrayList<>();
    private ArrayList<String> greetingsToTranslateList=new ArrayList<>();
    private ArrayList<Boolean> greetingsAnsweredCorrectList=new ArrayList<>();
    private ArrayList<Boolean> greetingsAnsweredWrongList=new ArrayList<>();
    private ArrayList<Boolean> numbersAnsweredCorrectList=new ArrayList<>();
    private ArrayList<Boolean> numbersAnsweredWrongList=new ArrayList<>();
    private ArrayList<Boolean> familyMembersAnsweredCorrectList=new ArrayList<>();
    private ArrayList<Boolean> familyMembersAnsweredWrongList=new ArrayList<>();
    private ArrayList<Boolean> timeAnsweredCorrectList=new ArrayList<>();

    public ArrayList<Boolean> getGreetingsAnsweredCorrectList() {
        return greetingsAnsweredCorrectList;
    }

    public void setGreetingsAnsweredCorrectList(ArrayList<Boolean> greetingsAnsweredCorrectList) {
        this.greetingsAnsweredCorrectList = greetingsAnsweredCorrectList;
    }

    public ArrayList<Boolean> getGreetingsAnsweredWrongList() {
        return greetingsAnsweredWrongList;
    }

    public void setGreetingsAnsweredWrongList(ArrayList<Boolean> greetingsAnsweredWrongList) {
        this.greetingsAnsweredWrongList = greetingsAnsweredWrongList;
    }

    public ArrayList<Boolean> getNumbersAnsweredCorrectList() {
        return numbersAnsweredCorrectList;
    }

    public void setNumbersAnsweredCorrectList(ArrayList<Boolean> numbersAnsweredCorrectList) {
        this.numbersAnsweredCorrectList = numbersAnsweredCorrectList;
    }

    public ArrayList<Boolean> getNumbersAnsweredWrongList() {
        return numbersAnsweredWrongList;
    }

    public void setNumbersAnsweredWrongList(ArrayList<Boolean> numbersAnsweredWrongList) {
        this.numbersAnsweredWrongList = numbersAnsweredWrongList;
    }

    public ArrayList<Boolean> getFamilyMembersAnsweredCorrectList() {
        return familyMembersAnsweredCorrectList;
    }

    public void setFamilyMembersAnsweredCorrectList(ArrayList<Boolean> familyMembersAnsweredCorrectList) {
        this.familyMembersAnsweredCorrectList = familyMembersAnsweredCorrectList;
    }

    public ArrayList<Boolean> getFamilyMembersAnsweredWrongList() {
        return familyMembersAnsweredWrongList;
    }

    public void setFamilyMembersAnsweredWrongList(ArrayList<Boolean> familyMembersAnsweredWrongList) {
        this.familyMembersAnsweredWrongList = familyMembersAnsweredWrongList;
    }

    public ArrayList<Boolean> getTimeAnsweredCorrectList() {
        return timeAnsweredCorrectList;
    }

    public void setTimeAnsweredCorrectList(ArrayList<Boolean> timeAnsweredCorrectList) {
        this.timeAnsweredCorrectList = timeAnsweredCorrectList;
    }

    public ArrayList<Boolean> getTimeAnsweredWrongList() {
        return timeAnsweredWrongList;
    }

    public void setTimeAnsweredWrongList(ArrayList<Boolean> timeAnsweredWrongList) {
        this.timeAnsweredWrongList = timeAnsweredWrongList;
    }

    private ArrayList<Boolean> timeAnsweredWrongList=new ArrayList<>();
    private ArrayList<String> numbersToTranslateList=new ArrayList<>();
    private ArrayList<String> coloursToTranslateList=new ArrayList<>();
    private ArrayList<String> familyMembersInfoToTranslateList=new ArrayList<>();
    private ArrayList<String> timeToTranslateList=new ArrayList<>();
    private ArrayList<String> travelToTranslateList=new ArrayList<>();

    private ArrayList<String> sentenceTranslatedList = new ArrayList<>();
    private ArrayList<String> greetingsTranslatedList=new ArrayList<>();
    private ArrayList<String> numbersTranslatedList=new ArrayList<>();
    private ArrayList<String> coloursTranslatedList=new ArrayList<>();
    private ArrayList<String> familyMembersInfoTranslatedList=new ArrayList<>();
    private ArrayList<String> timeTranslatedList=new ArrayList<>();
    private ArrayList<String> travelTranslatedList=new ArrayList<>();


    //Array Lists to store arrays of words that are for confusing the learner even if there are correct words
    private ArrayList<String[]> coloursWordConfuseList=new ArrayList<>();
    private ArrayList<String[]> wordSetConfuse = new ArrayList<>();
    private ArrayList<String[]> greetingsWordConfuseList=new ArrayList<>();
    private ArrayList<String[]> numbersWordConfuseList=new ArrayList<>();
    private ArrayList<String[]> familyMembersInfoWordConfuseList=new ArrayList<>();
    private ArrayList<String[]> timeWordConfuseList=new ArrayList<>();
    private ArrayList<String[]> travelWordConfuseList=new ArrayList<>();

    //Array Lists to store arrays of words that are correct
    private ArrayList<String[]> coloursWordCorrectList=new ArrayList<>();
    private ArrayList<String[]> wordSetCorrect = new ArrayList<>();
    private ArrayList<String[]> greetingsWordCorrectList=new ArrayList<>();
    private ArrayList<String[]> numbersWordCorrectList=new ArrayList<>();
    private ArrayList<String[]> familyMembersInfoWordCorrectList=new ArrayList<>();
    private ArrayList<String[]> timeWordCorrectList=new ArrayList<>();
    private ArrayList<String[]> travelWordCorrectList=new ArrayList<>();

    public ArrayList<Boolean> getSentencesAnsweredCorrectList() {
        return sentencesAnsweredCorrectList;
    }

    public void setSentencesAnsweredCorrectList(ArrayList<Boolean> sentencesAnsweredCorrectList) {
        this.sentencesAnsweredCorrectList = sentencesAnsweredCorrectList;
    }

    public ArrayList<Boolean> getSentencesAnsweredWrongList() {
        return sentencesAnsweredWrongList;
    }

    public void setSentencesAnsweredWrongList(ArrayList<Boolean> sentencesAnsweredWrongList) {
        this.sentencesAnsweredWrongList = sentencesAnsweredWrongList;
    }

    public ArrayList<String> getGreetingsToTranslateList() {
        return greetingsToTranslateList;
    }

    public void setGreetingsToTranslateList(ArrayList<String> greetingsToTranslateList) {
        this.greetingsToTranslateList = greetingsToTranslateList;
    }

    public ArrayList<String> getGreetingsTranslatedList() {
        return greetingsTranslatedList;
    }

    public void setGreetingsTranslatedList(ArrayList<String> greetingsTranslatedList) {
        this.greetingsTranslatedList = greetingsTranslatedList;
    }

    public ArrayList<String[]> getGreetingsWordCorrectList() {
        return greetingsWordCorrectList;
    }

    public void setGreetingsWordCorrectList(ArrayList<String[]> greetingsWordCorrectList) {
        this.greetingsWordCorrectList = greetingsWordCorrectList;
    }

    public ArrayList<String[]> getGreetingsWordConfuseList() {
        return greetingsWordConfuseList;
    }

    public void setGreetingsWordConfuseList(ArrayList<String[]> greetingsWordConfuseList) {
        this.greetingsWordConfuseList = greetingsWordConfuseList;
    }


    public void setNumbersToTranslateList(ArrayList<String> numbersToTranslateList) {
        this.numbersToTranslateList = numbersToTranslateList;
    }

    public ArrayList<String> getNumbersTranslatedList() {
        return numbersTranslatedList;
    }

    public void setNumbersTranslatedList(ArrayList<String> numbersTranslatedList) {
        this.numbersTranslatedList = numbersTranslatedList;
    }

    public ArrayList<String[]> getNumbersWordCorrectList() {
        return numbersWordCorrectList;
    }

    public void setNumbersWordCorrectList(ArrayList<String[]> numbersWordCorrectList) {
        this.numbersWordCorrectList = numbersWordCorrectList;
    }

    public ArrayList<String[]> getNumbersWordConfuseList() {
        return numbersWordConfuseList;
    }

    public void setNumbersWordConfuseList(ArrayList<String[]> numbersWordConfuseList) {
        this.numbersWordConfuseList = numbersWordConfuseList;
    }

    public ArrayList<String> getColoursToTranslateList() {
        return coloursToTranslateList;
    }

    public void setColoursToTranslateList(ArrayList<String> coloursToTranslateList) {
        this.coloursToTranslateList = coloursToTranslateList;
    }

    public ArrayList<String> getColoursTranslatedList() {
        return coloursTranslatedList;
    }

    public void setColoursTranslatedList(ArrayList<String> coloursTranslatedList) {
        this.coloursTranslatedList = coloursTranslatedList;
    }

    public ArrayList<String[]> getColoursWordCorrectList() {
        return coloursWordCorrectList;
    }

    public void setColoursWordCorrectList(ArrayList<String[]> coloursWordCorrectList) {
        this.coloursWordCorrectList = coloursWordCorrectList;
    }

    public ArrayList<String[]> getColoursWordConfuseList() {
        return coloursWordConfuseList;
    }

    public void setColoursWordConfuseList(ArrayList<String[]> coloursWordConfuseList) {
        this.coloursWordConfuseList = coloursWordConfuseList;
    }

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

    public ArrayList<String> getFamilyMembersInfoToTranslateList() {
        return familyMembersInfoToTranslateList;
    }

    public void setFamilyMembersInfoToTranslateList(ArrayList<String> familyMembersInfoToTranslateList) {
        this.familyMembersInfoToTranslateList = familyMembersInfoToTranslateList;
    }

    public ArrayList<String> getFamilyMembersInfoTranslatedList() {
        return familyMembersInfoTranslatedList;
    }

    public void setFamilyMembersInfoTranslatedList(ArrayList<String> familyMembersInfoTranslatedList) {
        this.familyMembersInfoTranslatedList = familyMembersInfoTranslatedList;
    }

    public ArrayList<String[]> getFamilyMembersInfoWordConfuseList() {
        return familyMembersInfoWordConfuseList;
    }

    public void setFamilyMembersInfoWordConfuseList(ArrayList<String[]> familyMembersInfoWordConfuseList) {
        this.familyMembersInfoWordConfuseList = familyMembersInfoWordConfuseList;
    }

    public ArrayList<String[]> getFamilyMembersInfoWordCorrectList() {
        return familyMembersInfoWordCorrectList;
    }

    public void setFamilyMembersInfoWordCorrectList(ArrayList<String[]> familyMembersInfoWordCorrectList) {
        this.familyMembersInfoWordCorrectList = familyMembersInfoWordCorrectList;
    }

    public ArrayList<String> getTimeToTranslateList() {
        return timeToTranslateList;
    }

    public void setTimeToTranslateList(ArrayList<String> timeToTranslateList) {
        this.timeToTranslateList = timeToTranslateList;
    }

    public ArrayList<String> getTimeTranslatedList() {
        return timeTranslatedList;
    }

    public void setTimeTranslatedList(ArrayList<String> timeTranslatedList) {
        this.timeTranslatedList = timeTranslatedList;
    }

    public ArrayList<String[]> getTimeWordCorrectList() {
        return timeWordCorrectList;
    }

    public void setTimeWordCorrectList(ArrayList<String[]> timeWordCorrectList) {
        this.timeWordCorrectList = timeWordCorrectList;
    }

    public ArrayList<String[]> getTimeWordConfuseList() {
        return timeWordConfuseList;
    }

    public void setTimeWordConfuseList(ArrayList<String[]> timeWordConfuseList) {
        this.timeWordConfuseList = timeWordConfuseList;
    }

    public ArrayList<String> getTravelToTranslateList() {
        return travelToTranslateList;
    }

    public void setTravelToTranslateList(ArrayList<String> travelToTranslateList) {
        this.travelToTranslateList = travelToTranslateList;
    }

    public ArrayList<String> getTravelTranslatedList() {
        return travelTranslatedList;
    }

    public void setTravelTranslatedList(ArrayList<String> travelTranslatedList) {
        this.travelTranslatedList = travelTranslatedList;
    }

    public ArrayList<String[]> getTravelWordCorrectList() {
        return travelWordCorrectList;
    }

    public void setTravelWordCorrectList(ArrayList<String[]> travelWordCorrectList) {
        this.travelWordCorrectList = travelWordCorrectList;
    }

    public ArrayList<String[]> getTravelWordConfuseList() {
        return travelWordConfuseList;
    }

    public void setTravelWordConfuseList(ArrayList<String[]> travelWordConfuseList) {
        this.travelWordConfuseList = travelWordConfuseList;
    }

    public ArrayList<String> getNumbersToTranslateList() {
        return numbersToTranslateList;
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
//        sentenceToTranslateList.add(4, "Samahani");

        for(int i=0;i<sentenceToTranslateList.size();i++){
            sentencesAnsweredCorrectList.add(i,false);
        }
        for(int i=0;i<sentenceToTranslateList.size();i++){
            sentencesAnsweredWrongList.add(i,false);
        }
        sentenceTranslatedList.add(0, "How are you?");
        sentenceTranslatedList.add(1, "What is your name?");
        sentenceTranslatedList.add(2, "Where do you live?");
        sentenceTranslatedList.add(3, "Thank you very much");
//        sentenceTranslatedList.add(4, "Excuse me");

        String[] wordConfuseOne={"Mine","Is","Why"};
        String[] wordConfuseTwo={"Yours","Are"};
        String[] wordConfuseThree= {"Leave","Does"};
        String[] wordConfuseFour={"Too","Thanks"};
//        String[] wordConfuseFive={"Excuses","You"};

        wordSetConfuse.add(0, wordConfuseOne);
        wordSetConfuse.add(1, wordConfuseTwo);
        wordSetConfuse.add(2, wordConfuseThree);
        wordSetConfuse.add(3, wordConfuseFour);
//        wordSetConfuse.add(4, wordConfuseFive);

        wordSetCorrect.add(0,new String[]{"are", "How", "you?"});
        wordSetCorrect.add(1,new String[]{"name?", "is", "What","your"});
        wordSetCorrect.add(2,new String[]{"do", "live?", "Where","you"});
        wordSetCorrect.add(3,new String[]{"much", "Thank", "very","you"});
//        wordSetCorrect.add(4,new String[]{"me", "Excuse"});
    }

    public void setupGreetingsToTranslate(){
        greetingsToTranslateList.add(0,"Habari Za Asubuhi");
        greetingsToTranslateList.add(1,"Habari za jioni");
        greetingsToTranslateList.add(2,"U hali gani");
        greetingsToTranslateList.add(3,"Tuonane kesho");
        greetingsToTranslateList.add(4,"Uende salama");

        greetingsTranslatedList.add(0,"Good morning");
        greetingsTranslatedList.add(1, "Good evening");
        greetingsTranslatedList.add(2,"How are you fairing?");
        greetingsTranslatedList.add(3,"Let's meet tomorrow");
        greetingsTranslatedList.add(4,"Go safely");

        greetingsWordCorrectList.add(0,new String[]{"morning", "Good"});
        greetingsWordCorrectList.add(1,new String[]{"evening","Good"});
        greetingsWordCorrectList.add(2,new String[]{"fairing","How","you","are"});
        greetingsWordCorrectList.add(3,new String[]{"meet","Let's", "tomorrow"});
        greetingsWordCorrectList.add(4,new String[]{"safely","Go"});

        greetingsWordConfuseList.add(0,new String[]{"mornings","god","moaning"});
        greetingsWordConfuseList.add(1,new String[]{"night","best"});
        greetingsWordConfuseList.add(2,new String[]{"why","fearing","is"});
        greetingsWordConfuseList.add(3,new String[]{"met","yesterday"});
        greetingsWordConfuseList.add(4,new String[]{"safety","went"});
    }

    public void setupNumbersToTranslate(){
        numbersToTranslateList.add(0,"Moja");
        numbersToTranslateList.add(1,"Mbili");
        numbersToTranslateList.add(2,"Tatu");
        numbersToTranslateList.add(3,"Nne");
        numbersToTranslateList.add(4,"Tano");

        numbersTranslatedList.add(0,"One");
        numbersTranslatedList.add(1, "Two");
        numbersTranslatedList.add(2,"Three");
        numbersTranslatedList.add(3,"Four");
        numbersTranslatedList.add(4,"Five");

        numbersWordCorrectList.add(0, new String[]{"One"});
        numbersWordCorrectList.add(1,new String[]{"Two"});
        numbersWordCorrectList.add(2,new String[]{"Three"});
        numbersWordCorrectList.add(3,new String[]{"Four"});
        numbersWordCorrectList.add(4,new String[]{"Five"});

        numbersWordConfuseList.add(0,new String[]{"Uno","On"});
        numbersWordConfuseList.add(1,new String[]{"Twi","Twa"});
        numbersWordConfuseList.add(2,new String[]{"Thre","Tree"});
        numbersWordConfuseList.add(3,new String[]{"Fou","For"});
        numbersWordConfuseList.add(4,new String[]{"Fi","ve"});
    }

    /*public void setupColoursToTranslate(){
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
    }*/

    public void setupFamilyContent(){
        familyMembersInfoToTranslateList.add(0,"Wazazi");
        familyMembersInfoToTranslateList.add(1,"Baba");
        familyMembersInfoToTranslateList.add(2,"Mama");
        familyMembersInfoToTranslateList.add(3,"Huyu ni dada yangu");
        familyMembersInfoToTranslateList.add(4,"Hawa ni wajomba wetu");

        familyMembersInfoTranslatedList.add(0,"Parents");
        familyMembersInfoTranslatedList.add(1,"Father");
        familyMembersInfoTranslatedList.add(2, "Mother");
        familyMembersInfoTranslatedList.add(3,"This is my sister");
        familyMembersInfoTranslatedList.add(4,"Those are our uncles");

        familyMembersInfoWordCorrectList.add(0,new String[]{"Parents"});
        familyMembersInfoWordCorrectList.add(1,new String[]{"Father"});
        familyMembersInfoWordCorrectList.add(2,new String[]{"Mother"});
        familyMembersInfoWordCorrectList.add(3,new String[]{"sister","is","This","my"});
        familyMembersInfoWordCorrectList.add(4,new String[]{"uncles","are","Those","our"});

        familyMembersInfoWordConfuseList.add(0,new String[]{"Parent","Nazi"});
        familyMembersInfoWordConfuseList.add(1,new String[]{"Brother","Dude"});
        familyMembersInfoWordConfuseList.add(2,new String[]{"Sister","Aunty"});
        familyMembersInfoWordConfuseList.add(3,new String[]{"are", "These"});
        familyMembersInfoWordConfuseList.add(4,new String[]{"Is","These"});
    }

    public void setupTimeToTranslate(){
        timeToTranslateList.add(0,"Asubuhi");
        timeToTranslateList.add(1,"Jioni");
        timeToTranslateList.add(2,"Mchana");
        timeToTranslateList.add(3,"Saa");
        timeToTranslateList.add(4,"Dakika");

        timeTranslatedList.add(0,"Morning");
        timeTranslatedList.add(1, "Evening");
        timeTranslatedList.add(2,"Daytime");
        timeTranslatedList.add(3,"Hour");
        timeTranslatedList.add(4,"Minute");

        timeWordCorrectList.add(0, new String[]{"Morning"});
        timeWordCorrectList.add(1,new String[]{"Evening"});
        timeWordCorrectList.add(2,new String[]{"Daytime"});
        timeWordCorrectList.add(3,new String[]{"Hour"});
        timeWordCorrectList.add(4,new String[]{"Minute"});

        timeWordConfuseList.add(0,new String[]{"Moning","Evening"});
        timeWordConfuseList.add(1,new String[]{"Morning","Night"});
        timeWordConfuseList.add(2,new String[]{"Day","Girl"});
        timeWordConfuseList.add(3,new String[]{"Hours","Our"});
        timeWordConfuseList.add(4,new String[]{"Minutes","Shop"});
    }

    public void setupTravelToTranslate(){
        travelToTranslateList.add(0,"Asubuhi");
        travelToTranslateList.add(1,"Jioni");
        travelToTranslateList.add(2,"Mchana");
        travelToTranslateList.add(3,"Saa");
        travelToTranslateList.add(4,"Dakika");

        travelTranslatedList.add(0,"Morning");
        travelTranslatedList.add(1, "Evening");
        travelTranslatedList.add(2,"Daytime");
        travelTranslatedList.add(3,"Hour");
        travelTranslatedList.add(4,"Minute");

        travelWordCorrectList.add(0, new String[]{"Morning"});
        travelWordCorrectList.add(1,new String[]{"Evening"});
        travelWordCorrectList.add(2,new String[]{"Daytime"});
        travelWordCorrectList.add(3,new String[]{"Hour"});
        travelWordCorrectList.add(4,new String[]{"Minute"});

        travelWordConfuseList.add(0,new String[]{"Moning","Evening"});
        travelWordConfuseList.add(1,new String[]{"Morning","Night"});
        travelWordConfuseList.add(2,new String[]{"Day","Girl"});
        travelWordConfuseList.add(3,new String[]{"Hours","Our"});
        travelWordConfuseList.add(4,new String[]{"Minutes","Shop"});
    }
}

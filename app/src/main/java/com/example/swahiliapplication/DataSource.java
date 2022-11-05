package com.example.swahiliapplication;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataSource {
    interface Local{

        ArrayList<PairModel> getPairs();

        QuestionModel getRandomQuestionObj();

        ArrayList<String> getAnswer();
    }

    interface Remote {

        FirebaseDatabase getDatabaseInstance();

        void setNewLanguage(String language);

        void setDailyXp(int xp);

        void setUserTotalXp(int xp);

        void setLastTimeVisited();

        void setDailyGoal(int dailyGoal);

        void setUserInfo(UserData userData);

        void setLessonComplete(String lesson, boolean completeness);

        void setLessonCompleteDate(String lesson);

        void getDailyGoal();

        void getDailyXp();

        void getWeekXp();

        void getLessonCompleted();
    }
}

package com.example.swahiliapplication.ui;

import com.example.swahiliapplication.FirebaseDatabaseHelper;
import com.example.swahiliapplication.QuestionDataSource;
import com.example.swahiliapplication.Repository;

public class Injection {


    public static Repository provideRepository() {

        return Repository.getInstance(
                QuestionDataSource.getInstance(),
                FirebaseDatabaseHelper.getHelperInstance());
    }

    public static FirebaseAuthHelper providesAuthHelper() {

        return FirebaseAuthHelper.getClassInstance();
    }
}


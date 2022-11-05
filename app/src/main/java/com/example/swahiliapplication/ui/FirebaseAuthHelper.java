package com.example.swahiliapplication.ui;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthHelper {

    public static FirebaseAuthHelper INSTANCE;
    public FirebaseAuth mAuth;

    public static FirebaseAuthHelper getClassInstance() {

        if (INSTANCE == null) {
            INSTANCE = new FirebaseAuthHelper();
        }

        return INSTANCE;
    }

    public FirebaseAuth getAuthInstance() {

        if (mAuth == null) {
            mAuth = FirebaseAuth.getInstance();
        }

        return mAuth;
    }
}

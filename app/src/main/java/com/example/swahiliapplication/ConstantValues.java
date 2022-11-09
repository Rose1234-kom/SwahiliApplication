package com.example.swahiliapplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ConstantValues {
    private static FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

    public static FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }
}

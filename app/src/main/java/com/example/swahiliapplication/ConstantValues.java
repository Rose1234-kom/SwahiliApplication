package com.example.swahiliapplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class ConstantValues {
    private static FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();

    public static FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseFirestore getFirebaseFirestore() {
        return firebaseFirestore;
    }
}

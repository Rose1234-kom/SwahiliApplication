package com.example.swahiliapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.swahiliapplication.ConstantValues;
import com.example.swahiliapplication.R;
import com.example.swahiliapplication.SwahiliLevels;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FirebaseUser firebaseUser= ConstantValues.getFirebaseAuth().getCurrentUser();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseUser != null) {
                    Intent mainIntent = new Intent(SplashScreen.this, SwahiliLevels.class);
                    SplashScreen.this.startActivity(mainIntent);
                    SplashScreen.this.finish();
                } else {
                    Intent mainIntent = new Intent(SplashScreen.this, WelcomeActivity.class);
                    SplashScreen.this.startActivity(mainIntent);
                    SplashScreen.this.finish();
                }
            }
        }, 3000);
    }
}
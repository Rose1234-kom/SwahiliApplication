package com.example.swahiliapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.swahiliapplication.R;
import com.example.swahiliapplication.SwahiliLevels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
    
    @BindView(R.id.get_started_button)
    //Button getStartedButton;
    AppCompatButton getStartedButton;
    
    @BindView(R.id.log_in_linkbtn)
    Button LogInLinkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);
        getStartedButton = findViewById(R.id.get_started_button);
        
        initData();
    }

    private void initData() {
        
        getStartedButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                goToGetStartedScreen();
            }
        });

        LogInLinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToSignInScreen();
            }
        });
    }

    private void goToSignInScreen() {
        startActivity(new Intent(WelcomeActivity.this,SignInActivity.class));
        finish();
    }


    private void goToGetStartedScreen() {
        startActivity(new Intent(WelcomeActivity.this,CreateAccountActivity.class));
        finish();
    }


}
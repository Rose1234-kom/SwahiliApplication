package com.example.swahiliapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swahiliapplication.ui.WelcomeActivity;

public class SettingsPage extends AppCompatActivity {
    TextView logoutText;
    ConstantValues constantValues=new ConstantValues();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        logoutText=findViewById(R.id.log_out);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ConstantValues.getFirebaseAuth().getCurrentUser()!=null) {
                    ConstantValues.getFirebaseAuth().signOut();
                    startActivity(new Intent(SettingsPage.this, WelcomeActivity.class));
                }
            }
        });
    }

}
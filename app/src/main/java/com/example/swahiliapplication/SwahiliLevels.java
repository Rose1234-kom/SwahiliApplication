package com.example.swahiliapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SwahiliLevels extends AppCompatActivity {
    AppCompatButton beginnerBtn;
    AppCompatButton intermediateBtn;
    AppCompatButton advancedBtn;


    public static void showToast(Context context,String message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swahili_levels);

        beginnerBtn=findViewById(R.id.button_beginner);
        intermediateBtn=findViewById(R.id.button_intermediate);
        advancedBtn=findViewById(R.id.button_advanced);

        beginnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SwahiliLevels.this,BeginnerList.class);
                startActivity(intent);
            }
        });

    }
}
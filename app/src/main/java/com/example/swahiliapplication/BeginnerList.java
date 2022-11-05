package com.example.swahiliapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.swahiliapplication.ui.WordTaskActivity;

public class BeginnerList extends AppCompatActivity {
    AppCompatButton introduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_list);

        introduction = findViewById(R.id.button);

        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeginnerList.this, WordTaskActivity.class);
                startActivity(intent);
            }
        });


    }
}
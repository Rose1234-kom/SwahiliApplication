package com.example.swahiliapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.swahiliapplication.ui.WordTaskActivity;

public class IntermediateList extends AppCompatActivity {
    private AppCompatButton family, time, travel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_list);
        family=findViewById(R.id.family_button);
        time=findViewById(R.id.time_button);
        travel=findViewById(R.id.travel_button);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntermediateList.this, WordTaskActivity.class);
                intent.putExtra("Action","Family");
                startActivity(intent);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IntermediateList.this, WordTaskActivity.class);
                intent.putExtra("Action","Time");
                startActivity(intent);
            }
        });
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IntermediateList.this, WordTaskActivity.class);
                intent.putExtra("Action","Travel");
                startActivity(intent);
            }
        });
    }
}
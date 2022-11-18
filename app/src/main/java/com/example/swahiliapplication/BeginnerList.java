package com.example.swahiliapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.swahiliapplication.Models.LearnerProgress;
import com.example.swahiliapplication.ui.WordTaskActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BeginnerList extends AppCompatActivity {
    AppCompatButton introduction, greetings, numbers, colours;
    ConstantValues constantValues=new ConstantValues();
    ArrayList<LearnerProgress> learnerProgresses=new ArrayList<>();
    String levelId;
    LearnerProgress learnerProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_list);


        introduction = findViewById(R.id.button);
        greetings=findViewById(R.id.button2);
        numbers=findViewById(R.id.button3);




    }

    @Override
    protected void onStart() {
        super.onStart();
        getUserLevelProg();

        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeginnerList.this, WordTaskActivity.class);
                intent.putExtra("Action","Introduction");
                intent.putExtra("levelID",levelId);
                startActivity(intent);
            }
        });
        greetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BeginnerList.this, WordTaskActivity.class);
                intent.putExtra("Action","Greetings");
                intent.putExtra("levelID",levelId);
                startActivity(intent);

            }
        });
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BeginnerList.this, WordTaskActivity.class);
                intent.putExtra("Action","Numbers");
                intent.putExtra("levelID",levelId);
                startActivity(intent);
            }
        });

    }

    private void getUserLevelProg(){
        Query beginnerRef=constantValues.getFirebaseFirestore().collection("Users").document(ConstantValues.getFirebaseAuth().getCurrentUser().getUid()).collection("LearnerProgress");
        beginnerRef.whereEqualTo("userLevel", "Beginner").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot documentSnapshot:task.getResult()){
                        learnerProgress=documentSnapshot.toObject(LearnerProgress.class);
                        levelId=documentSnapshot.getId();
                    }
                }
            }
        });
    }
}
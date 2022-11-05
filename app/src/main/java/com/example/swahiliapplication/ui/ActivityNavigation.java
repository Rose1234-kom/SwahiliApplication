package com.example.swahiliapplication.ui;

import android.content.Context;
import android.content.Intent;

import com.example.swahiliapplication.LessonCompletedActivity;

import java.util.ArrayList;
import java.util.Random;

public class ActivityNavigation {

    static  ActivityNavigation INSTANCE;

    Context context;

    ArrayList<Class> activities = new ArrayList<>();

    Random random = new Random();

    public ActivityNavigation(Context context) {

        this.context=context;

        initData();

    }


    public static ActivityNavigation getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new ActivityNavigation(context);
        }

        return INSTANCE;
    }

    private void initData() {

        activities.add(WordTaskActivity.class);
        activities.add(TranslateSentence.class);
        activities.add(TapPairActivity.class);
    }

    public void takeToRandomTask() {

        int randomIndex = random.nextInt(activities.size());

        Intent intent = new Intent(context, activities.get(randomIndex));
        context.startActivity(intent);
    }

    public void lessonCompleted(){

        Intent intent = new Intent(context, LessonCompletedActivity.class);
        context.startActivity(intent);
    }

   
}

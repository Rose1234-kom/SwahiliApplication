package com.example.swahiliapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.swahiliapplication.bottomnavigationfragmentdemo.HomeFragment;
import com.example.swahiliapplication.bottomnavigationfragmentdemo.NotificationFragment;
import com.example.swahiliapplication.bottomnavigationfragmentdemo.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SwahiliLevels extends Fragment {
    AppCompatButton beginnerBtn;
    AppCompatButton intermediateBtn;
    AppCompatButton advancedBtn;



    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    View view;

    public static void showToast(Context context,String message) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_swahili_levels, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        beginnerBtn=view.findViewById(R.id.button_beginner);
        intermediateBtn=view.findViewById(R.id.button_intermediate);
        advancedBtn=view.findViewById(R.id.button_advanced);


    }

    @Override
    public void onStart() {
        super.onStart();
        beginnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),BeginnerList.class);
                startActivity(intent);
            }
        });
    }
}
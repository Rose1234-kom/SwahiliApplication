package com.example.swahiliapplication.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.swahiliapplication.R;
import com.example.swahiliapplication.SettingsPage;
import com.example.swahiliapplication.SwahiliLevels;
import com.example.swahiliapplication.bottomnavigationfragmentdemo.NotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        loadFragment(new SwahiliLevels());
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment=null;
                switch (item.getItemId()){
                    case R.id.swahiliClassFragment:
                        fragment=new SwahiliLevels();
                        break;
                    case R.id.swahiliProfile:
                        fragment=new NotificationFragment();
                        break;

                    case R.id.swahiliHighScore:
                        fragment=new SettingsPage();
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }

    private Boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_navigation_frame, fragment).addToBackStack(null).commit();
            return true;
        }
        return false;
    }
}

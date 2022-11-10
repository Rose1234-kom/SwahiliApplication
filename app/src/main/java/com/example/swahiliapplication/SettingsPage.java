package com.example.swahiliapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingsPage extends Fragment {

    View view;
    TextView logoutText;
    ConstantValues constantValues=new ConstantValues();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_settings_page,container,false);
        logoutText=view.findViewById(R.id.log_out);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ConstantValues.getFirebaseAuth().getCurrentUser()!=null) {
                    ConstantValues.getFirebaseAuth().signOut();
                }
            }
        });
    }
}
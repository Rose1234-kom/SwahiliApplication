package com.example.swahiliapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swahiliapplication.ConstantValues;
import com.example.swahiliapplication.Models.UserInformation;
import com.example.swahiliapplication.R;
import com.example.swahiliapplication.SettingsPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class UserProfile extends Fragment {
    View view;
    ShapeableImageView userImage,settingsImage;
    MaterialTextView userNameText, levelText, purposeText, goalText, nationalityText;
    ConstantValues constantValues=new ConstantValues();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.user_profile_layout, container, false);
        userImage=view.findViewById(R.id.user_profile_img);
        settingsImage=view.findViewById(R.id.settings_img);
        userNameText=view.findViewById(R.id.username_text);
        levelText=view.findViewById(R.id.level_text);
        purposeText=view.findViewById(R.id.purpose_text);
        goalText=view.findViewById(R.id.goal_text);
        nationalityText=view.findViewById(R.id.nationality_text);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllUserProfileInfo();
        settingsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsPage.class));
            }
        });
    }

    public void getAllUserProfileInfo(){
        DocumentReference userRef=constantValues.getFirebaseFirestore().collection("Users").document(ConstantValues.getFirebaseAuth().getCurrentUser().getUid());
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot snapshot=task.getResult();
                    if(snapshot.exists()){
                        UserInformation userInformation = snapshot.toObject(UserInformation.class);
                        userNameText.setText(userInformation.getUserName());
                        for(String s:userInformation.getLearningPurposes()) {
                            purposeText.setText(s);
                        }
                        goalText.setText(userInformation.getDailyGoal());
                        nationalityText.setText(userInformation.getCountry());
                    }
                }
            }
        });
    }
}

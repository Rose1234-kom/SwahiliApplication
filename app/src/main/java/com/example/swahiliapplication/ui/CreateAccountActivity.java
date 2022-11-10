package com.example.swahiliapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.swahiliapplication.ConstantValues;
import com.example.swahiliapplication.Models.UserInformation;
import com.example.swahiliapplication.Models.UserName;
import com.example.swahiliapplication.R;
import com.example.swahiliapplication.SwahiliLevels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity {

    EditText userNameText,passwordEditText,confirmPasswordEditText;
    AppCompatButton createAccountBtn;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    ConstantValues constantValues=new ConstantValues();
    SharedPreferences emailPref;
    String emailAddress, userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        emailPref=getSharedPreferences("emailAddress", MODE_PRIVATE);
        emailAddress=emailPref.getString("emailAddress","");
        userNameText =findViewById(R.id.username_edit_text);
        createAccountBtn=findViewById(R.id.create_account_btn);
        progressBar=findViewById(R.id.layout_progress_bar);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
handleReceivedFirebaseLink();
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }

    String userName;
    private void createAccount() {
        userName = userNameText.getText().toString();
        boolean isValidated = validateData(userName);
        if(!isValidated){
            return;
        }
    }

    private void handleReceivedFirebaseLink(){
        FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent()).addOnSuccessListener(new OnSuccessListener<PendingDynamicLinkData>() {
            @Override
            public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                Uri signUpLink = null;
                if (pendingDynamicLinkData != null) {
                    signUpLink = pendingDynamicLinkData.getLink();
                } else {

                }
                if (signUpLink != null) {
                    String verifyEmailLink = getIntent().getData().toString();
                    if (ConstantValues.getFirebaseAuth().isSignInWithEmailLink(verifyEmailLink)) {
                        if (emailAddress != null) {
                            try {
                                ConstantValues.getFirebaseAuth().signInWithEmailLink(emailAddress, verifyEmailLink).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            AuthResult authResult = task.getResult();
                                            if (authResult.getAdditionalUserInfo().isNewUser()) {
                                                userId=authResult.getUser().getUid();
                                                initialiseData(authResult.getUser().getUid());
                                            }
                                            if (authResult.getUser().isEmailVerified()) {
                                                readExistingUserInfo(authResult.getUser().getUid());
                                            } else {

                                            }
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {

                    }
                }
            }
        });
    }

    private void initialiseData(String userId){
        UserInformation userInformation = new UserInformation();
        ArrayList<String> learnPurposes=new ArrayList<>();
        learnPurposes.add("");
        userInformation.setUserId(userId);
        userInformation.setEmailAddress(emailAddress);
        userInformation.setUserName("");
        userInformation.setCountry("");
        userInformation.setDailyGoal("");
        userInformation.setLearningPurposes(learnPurposes);
        DocumentReference ref = constantValues.getFirebaseFirestore().collection("Users").document(userId);
        ref.set(userInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }else{

                }
            }
        });
    }

    private void saveFirebaseData() {
        progressDialog.setMessage("Saving Account info...");

        // set data to save

        UserInformation userInformation = new UserInformation();
        userInformation.setUserId(firebaseAuth.getUid());
        userInformation.setUserName(userName);
        userInformation.setEmailAddress(emailAddress);

        UserName userNameMap = new UserName();

        DocumentReference ref = constantValues.getFirebaseFirestore().collection("Users").document(userName);
        DocumentReference userNameRef = constantValues.getFirebaseFirestore().collection("UserName").document(userName);
        userNameRef.set(userNameMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }else{

                }
            }
        });
        ref.update("userName",userName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    startActivity(new Intent(CreateAccountActivity.this, SwahiliLevels.class));
                    finish();
                }else{
                    progressDialog.dismiss();
                }
            }
        });
    }

    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            createAccountBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            createAccountBtn.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String userName){
        //validate the data that are input by user
        DocumentReference userNameRef = constantValues.getFirebaseFirestore().collection("UserName").document(userName);
        userNameRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot snapshot=task.getResult();
                    if(snapshot.exists()){
                        userNameText.setError("Sorry. This username has been taken");
                        userNameText.requestFocus();
                        return;
                    }else{
                        saveFirebaseData();
                    }
                }
            }
        });
        return true;
    }

    private void readExistingUserInfo(String userId){
        DocumentReference reference=constantValues.getFirebaseFirestore().collection("Users").document(userId);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot snapshot=task.getResult();
                    if(snapshot.exists()){
                        //Check for username and email
                        startActivity(new Intent(CreateAccountActivity.this, SwahiliLevels.class));
                        finish();
                    }else{

                    }
                }
            }
        });
    }
}
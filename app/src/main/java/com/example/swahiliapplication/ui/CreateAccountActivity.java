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
import android.widget.Toast;

import com.example.swahiliapplication.ConstantValues;
import com.example.swahiliapplication.Models.CurrProgress;
import com.example.swahiliapplication.Models.LearnerProgress;
import com.example.swahiliapplication.Models.UserInformation;
import com.example.swahiliapplication.Models.UserName;
import com.example.swahiliapplication.R;
import com.example.swahiliapplication.SwahiliLevels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity {

    EditText userNameText,passwordEditText,confirmPasswordEditText;
    AppCompatButton createAccountBtn;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    ConstantValues constantValues=new ConstantValues();
    SharedPreferences emailPref;
    String emailAddress, userId,countryGet, purpose;
    MaterialAutoCompleteTextView country, learnPurpose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        emailPref=getSharedPreferences("emailAddress", MODE_PRIVATE);
        emailAddress=emailPref.getString("emailAddress","");
        country=findViewById(R.id.country_select);
        learnPurpose=findViewById(R.id.purpose_learn);
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
        countryGet=country.getText().toString();
        purpose=learnPurpose.getText().toString();
        validateData(userName,countryGet,purpose);
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
                                                initialiseData(authResult.getUser().getUid(),authResult.getUser().getEmail());
                                            }
                                            if (!authResult.getAdditionalUserInfo().isNewUser()&&authResult.getUser().isEmailVerified()) {
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

    private void initialiseData(String userId,String emailAddress){
        UserInformation userInformation = new UserInformation();
        ArrayList<String> learnPurposes=new ArrayList<>();
        learnPurposes.add("");
        userInformation.setUserId(userId);
        userInformation.setEmailAddress(emailAddress);
        userInformation.setUserName("");
        userInformation.setCountry("");
        userInformation.setDailyGoal("");
        userInformation.setLearningPurposes(learnPurposes);
        LearnerProgress learnerProgressBeginner=new LearnerProgress();
        LearnerProgress learnerProgressIntermediate=new LearnerProgress();
        LearnerProgress learnerProgressAdvanced=new LearnerProgress();
        learnerProgressBeginner.setUserId(userId);
        learnerProgressBeginner.setUserScore(0);
        learnerProgressBeginner.setUserLevel("Beginner");
        learnerProgressIntermediate.setUserId(userId);
        learnerProgressIntermediate.setUserScore(0);
        learnerProgressIntermediate.setUserLevel("Intermediate");
        learnerProgressAdvanced.setUserId(userId);
        learnerProgressAdvanced.setUserScore(0);
        learnerProgressAdvanced.setUserLevel("Advanced");
        CurrProgress currProgressBegin=new CurrProgress();
        CurrProgress currProgressInt=new CurrProgress();
        CurrProgress currProgressAdv=new CurrProgress();
        currProgressBegin.setUserId(userId);
        currProgressInt.setUserId(userId);
        currProgressAdv.setUserId(userId);
        DocumentReference ref = constantValues.getFirebaseFirestore().collection("Users").document(userId);
        ref.set(userInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }else{

                }
            }
        });
        DocumentReference learnerProgressBegin=ref.collection("LearnerProgress").document();
        DocumentReference beginnerLearner=ref.collection("LearnerProgress").document(learnerProgressBegin.getId());
        DocumentReference learnerProgressIntermed=ref.collection("LearnerProgress").document();
        DocumentReference intermediateLearner=ref.collection("LearnerProgress").document(learnerProgressIntermed.getId());
        DocumentReference learnerProgressAdvance=ref.collection("LearnerProgress").document();
        DocumentReference advancedLearner=ref.collection("LearnerProgress").document(learnerProgressAdvance.getId());
        DocumentReference currBeginnerLearner=ref.collection("CurrentProgress").document(beginnerLearner.getId());
        DocumentReference currIntermedLearner=ref.collection("CurrentProgress").document(intermediateLearner.getId());
        DocumentReference currAdvLearner=ref.collection("CurrentProgress").document(advancedLearner.getId());
        beginnerLearner.set(learnerProgressBeginner).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }
            }
        });
        intermediateLearner.set(learnerProgressIntermediate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }
            }
        });
        advancedLearner.set(learnerProgressAdvanced).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }
            }
        });
        currBeginnerLearner.set(currProgressBegin).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
        currIntermedLearner.set(currProgressInt).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
        currAdvLearner.set(currProgressAdv).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    private void saveFirebaseData(String userName,String countryGet, String purpose) {
        progressDialog.setMessage("Saving Account info...");

        // set data to save
        ArrayList<String> learnPurpose=new ArrayList<>();
        learnPurpose.add(purpose);
        UserInformation userInformation = new UserInformation();
        userInformation.setUserName(userName);
        userInformation.setCountry(countryGet);
        userInformation.setLearningPurposes(learnPurpose);

        UserName userNameMap = new UserName();

        DocumentReference ref = constantValues.getFirebaseFirestore().collection("Users").document(ConstantValues.getFirebaseAuth().getCurrentUser().getUid());
        DocumentReference userNameRef = constantValues.getFirebaseFirestore().collection("UserName").document(userName);
        userNameRef.set(userNameMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }else{

                }
            }
        });
        ref.update("userName",userName,"country",countryGet, "learningPurposes", learnPurpose).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){


                }else{
                    progressDialog.dismiss();
                    Toast.makeText(CreateAccountActivity.this, "Sorry. Something went wrong. Please retry later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        progressDialog.dismiss();
        startActivity(new Intent(CreateAccountActivity.this, HomeActivity.class));
        finish();
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

    void validateData(String userName, String country, String purpose){
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
                        saveFirebaseData(userName,country, purpose);
                    }
                }
            }
        });
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
                        startActivity(new Intent(CreateAccountActivity.this, HomeActivity.class));
                        finish();
                    }else{

                    }
                }
            }
        });
    }
}
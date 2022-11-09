package com.example.swahiliapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swahiliapplication.ConstantValues;
import com.example.swahiliapplication.R;
import com.example.swahiliapplication.SwahiliLevels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.HashMap;

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
        final String timestamp = "" + System.currentTimeMillis();
        HashMap <String, Object > hashMap = new HashMap<>();
        hashMap.put("uid", ""+ userId);
        hashMap.put("email",""+ emailAddress);
        hashMap.put("userName","");
        hashMap.put("timestamp", ""+timestamp);
        DatabaseReference ref = constantValues.getFirebaseDatabase().getReference("Users").child(userId);
        ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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

        final String timestamp = "" + System.currentTimeMillis();
        // set data to save

        HashMap <String, Object > hashMap = new HashMap<>();
        hashMap.put("uid", ""+ firebaseAuth.getUid());
        hashMap.put("email",""+ userName);
        hashMap.put("timestamp", ""+timestamp);


        HashMap <String, Object > userNameMap = new HashMap<>();
        hashMap.put("uid", ""+ userId);
        hashMap.put("timestamp", ""+timestamp);

        HashMap <String, Object > userNameUpdMap = new HashMap<>();
        hashMap.put("userName", ""+ userName);
        hashMap.put("timestamp", ""+timestamp);


        DatabaseReference ref = constantValues.getFirebaseDatabase().getReference("Users");
        DatabaseReference userNameRef = constantValues.getFirebaseDatabase().getReference("UserName").child(userName);
        userNameRef.setValue(userNameMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                }else{

                }
            }
        });

        ref.child(userId).updateChildren(userNameUpdMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        DatabaseReference userNameRef = constantValues.getFirebaseDatabase().getReference("UserName").child(userName);
        userNameRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot snapshot=task.getResult();
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
        DatabaseReference reference=constantValues.getFirebaseDatabase().getReference("Users").child(userId);
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot snapshot=task.getResult();
                    if(snapshot.exists()){
                        //Check for username and email
                        startActivity(new Intent(CreateAccountActivity.this, SwahiliLevels.class));
                    }else{

                    }
                }
            }
        });
    }
}
package com.example.swahiliapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swahiliapplication.R;
import com.example.swahiliapplication.SwahiliLevels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CreateAccountActivity extends AppCompatActivity {

    EditText emailEditText,passwordEditText,confirmPasswordEditText;
    AppCompatButton createAccountBtn;
    ProgressBar progressBar;
    TextView loginBtnTextView;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        emailEditText=findViewById(R.id.email_edit_text);
        passwordEditText=findViewById(R.id.password_edit_text);
        confirmPasswordEditText=findViewById(R.id.confirm_password_edit_text);
        createAccountBtn=findViewById(R.id.create_account_btn);
        progressBar=findViewById(R.id.layout_progress_bar);
        loginBtnTextView=findViewById(R.id.log_text_view_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
        loginBtnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    String email, password, confirmPassword;
    private void createAccount() {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        confirmPassword = confirmPasswordEditText.getText().toString();

        boolean isValidated = validateData(email,password,confirmPassword);
        if(!isValidated){
            return;
        }
//        if(password.length()<6){
//            Toast.makeText(this, "Password should be atleast 6 character ling ", Toast.LENGTH_SHORT).show();
//        }
//        if(!password.equals(confirmPassword)){
//            Toast.makeText(this,"Password do not match ", Toast.LENGTH_SHORT).show();
//        }
        
        createAccountInFirebase();
    }

    private void createAccountInFirebase() {
        //changeInProgress(true);
        progressDialog.setMessage("Creating account ...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // account created
                saveFirebaseData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // failed creating account
                progressDialog.dismiss();
                Toast.makeText(CreateAccountActivity.this,""+ e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void saveFirebaseData() {
        progressDialog.setMessage("Saving Account info...");

        final String timestamp = "" + System.currentTimeMillis();
        // set data to save

        HashMap <String, Object > hashMap = new HashMap<>();
        hashMap.put("uid", ""+ firebaseAuth.getUid());
        hashMap.put("email",""+email);
        hashMap.put("timestamp", ""+timestamp);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //db updated
                progressDialog.dismiss();
                startActivity(new Intent(CreateAccountActivity.this, SignInActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // failed updating db
                progressDialog.dismiss();
                startActivity(new Intent(CreateAccountActivity.this, SignInActivity.class));
                finish();
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

    boolean validateData(String email,String password,String confirmPassword){
        //validate the data that are input by user

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            passwordEditText.setError("Password length is invalid");
            return false;
        }
        if(!password.equals(confirmPassword)){
            confirmPasswordEditText.setError("Password not matched");
            return false;
        }
        return true;
    }
}
package com.example.swahiliapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swahiliapplication.MainActivity;
import com.example.swahiliapplication.R;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.swahiliapplication.SwahiliLevels;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;



public class SignInActivity extends AppCompatActivity {

       EditText emailEditText,passwordEditText;
       AppCompatButton loginBtn;
       ProgressBar progressBar;
       TextView createAccountBtnTextView;
       private  FirebaseAuth firebaseAuth;
       private ProgressDialog progressDialog;

       @Override
     protected void onCreate(Bundle savedInstanceState) {

           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_sign_in);

           emailEditText=findViewById(R.id.email_edit_text);
           passwordEditText=findViewById(R.id.password_edit_text);
           loginBtn=findViewById(R.id.login_btn);
           progressBar=findViewById(R.id.layout_progress_bar);
           createAccountBtnTextView=findViewById(R.id.create_account_text_view_btn);

           firebaseAuth = FirebaseAuth.getInstance();
           progressDialog = new ProgressDialog(this);
           progressDialog.setTitle("Please wait...");
           progressDialog.setCanceledOnTouchOutside(false);

           loginBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   loginUser();
               }
           });
           createAccountBtnTextView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(SignInActivity.this, CreateAccountActivity.class);
                   startActivity(intent);
               }
           });
       }

       String email, password;
    private void loginUser() {
         email = emailEditText.getText().toString();
         password = passwordEditText.getText().toString();


        boolean isValidated = validateData(email,password);
        if(!isValidated){
            return;
        }
        progressDialog.setMessage("Logging in ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // logged in
                        progressDialog.dismiss();
                        startActivity(new Intent(SignInActivity.this, SwahiliLevels.class));
                        finish();
                    }
                });

        //loginAccountInFirebase(email,password);
    }

    void loginAccountInFirebase(String email,String password){
           FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
           changeInProgress(true);
           firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   changeInProgress(false);
                   if(task.isSuccessful()){
                       //login is success
                       if(firebaseAuth.getCurrentUser().isEmailVerified()){
                           //go to mainactivity
                           startActivity(new Intent(SignInActivity.this, SwahiliLevels.class));
                       }else {
                           SwahiliLevels.showToast(SignInActivity.this,"Email not verified,Please verify your email.");

                       }
                   }else{
                       //login failed
                       SwahiliLevels.showToast(SignInActivity.this,task.getException().getLocalizedMessage());
                   }
               }
           });

    }

    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String password){
        //validate the data that are input by user

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            passwordEditText.setError("Password length is invalid");
            return false;
        }

        return true;
    }

}
package com.example.swahiliapplication.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.swahiliapplication.ConstantValues;
import com.example.swahiliapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;

import java.util.Objects;
import java.util.Random;

import butterknife.BindView;

public class SignInActivity extends AppCompatActivity {
    private ConstantValues constantValues=new ConstantValues();

    EditText emailText;

    private String emailGet;
    AppCompatButton loginButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailText=findViewById(R.id.email_id_text);
        loginButton=findViewById(R.id.login_btn);

    }



    @Override
    protected void onStart() {
        super.onStart();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    signInWithLink();
            }
        });
    }
    private void signInWithLink(){
        emailGet= Objects.requireNonNull(emailText.getText()).toString().trim();
        if(emailGet.isEmpty()){
            emailText.setError("Sorry. This is required");
            emailText.requestFocus();
            return;
        }
        ActionCodeSettings actionCodeSettings =
                ActionCodeSettings.newBuilder()
                        .setUrl("https://swahiliapplication-e6b01.firebaseapp.com/verify?uid=".concat(String.valueOf(new Random().nextInt(1000))))
                        // This must be true
                        .setHandleCodeInApp(true)
                        .setAndroidPackageName(
                                "com.example.swahiliapplication",
                                false, /* installIfNotAvailable */
                                null    /* minimumVersion */)
                        .build();
        ConstantValues.getFirebaseAuth().sendSignInLinkToEmail(emailGet,actionCodeSettings).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    emailText.setText("", TextView.BufferType.EDITABLE);
                    Toast.makeText(SignInActivity.this, "Kindly check your mailbox for sign in instructions", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignInActivity.this, task.getException().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("emailAddress", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("emailAddress", emailGet);
        editor.putString("extraAction", getIntent().getStringExtra("extraAction"));
        editor.apply();

    }
}

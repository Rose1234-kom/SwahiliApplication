package com.example.swahiliapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import com.example.swahiliapplication.R
import android.content.Intent
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swahiliapplication.ui.CreateAccountActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.example.swahiliapplication.SwahiliLevels
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener

class SignInActivity : AppCompatActivity() {
    lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    lateinit var loginBtn: AppCompatButton
    lateinit var createAccountBtnTextView: TextView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var mGoogleApiClient: GoogleApiClient

     fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_sign_in,container,false)
        view.apply {
            loginBtn = findViewById(R.id.login_btn)
            emailEditText = findViewById(R.id.email_edit_text)
            passwordEditText = findViewById(R.id.password_edit_text)


            firebaseAuth = FirebaseAuth.getInstance()

            loginBtn.setOnClickListener { loginFun() }
        }
        return view
    }

    private fun loginFun() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        var error = 0



        if ( TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            error = 1
        }

        if(error!=1){

        }


        when (error) {
            1 -> {
                if(TextUtils.isEmpty(email))
                    passwordEditText.error = "must not be empty"
                if(TextUtils.isEmpty(password))
                    passwordEditText.error = "must not be empty"
            }
        }
    }

    private fun signIn(it: Any, email: String, password: String) {

    }







    fun validateData(email: String?, password: String): Boolean {
        //validate the data that are input by user
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText!!.error = "Email is invalid"
            return false
        }
        if (password.length < 6) {
            passwordEditText!!.error = "Password length is invalid"
            return false
        }
        return true
    }
}
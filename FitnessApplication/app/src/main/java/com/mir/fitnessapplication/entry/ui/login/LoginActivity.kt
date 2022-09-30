package com.mir.fitnessapplication.entry.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.register.RegisterActivity
import com.mir.fitnessapplication.main.MainActivity


class LoginActivity : AppCompatActivity(){

    private var loginTextFiled: EditText = findViewById(R.id.editTextTextEmailAddress)
    private var passwordTextFiled: EditText = findViewById(R.id.editTextTextPassword)
    private var forgotPasswordText: TextView = findViewById(R.id.forgotPasswordText)
    private var entryButton: Button = findViewById(R.id.loginEnterButton)
    private var registerText: TextView = findViewById(R.id.loginRegisterText)
    private var wrongUsernameOrPassword: TextView = findViewById(R.id.wrongUsernameOrPassword)
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        forgotPasswordText.setOnClickListener{
            Log.d("tag", "account password reset activity intent")
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) //TODO Доделать активити сброса пароля
        }
        entryButton.setOnClickListener{
            Log.d("tag", "Enter account activity intent")

            if (!TextUtils.isEmpty(loginTextFiled.text) && !TextUtils.isEmpty(passwordTextFiled.text)) {

            }
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))

        }
        registerText.setOnClickListener{
            Log.d("tag", "Register activity intent")
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        loginTextFiled.setOnClickListener{
            wrongUsernameOrPassword.visibility = TextView.INVISIBLE
        }
        passwordTextFiled.setOnClickListener{
            wrongUsernameOrPassword.visibility = TextView.INVISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = firebaseAuth.currentUser

        if (currentUser != null) {
            var start = startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }



}
package com.mir.fitnessapplication.entry.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.register.RegisterActivity
import com.mir.fitnessapplication.entry.ui.register.data.FirebaseURL
import com.mir.fitnessapplication.main.MainActivity


class LoginActivity : AppCompatActivity(){

    private var loginTextFiled: EditText? = null
    private var passwordTextFiled: EditText? = null
    private var forgotPasswordText: TextView? = null
    private var entryButton: Button? = null
    private var registerText: TextView? = null
    private var wrongUsernameOrPassword: TextView? = null
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginTextFiled = findViewById(R.id.editTextTextEmailAddress)
        passwordTextFiled  = findViewById(R.id.editTextTextPassword)
        forgotPasswordText = findViewById(R.id.forgotPasswordText)
        entryButton = findViewById(R.id.loginEnterButton)
        registerText = findViewById(R.id.loginRegisterText)
        wrongUsernameOrPassword = findViewById(R.id.wrongUsernameOrPassword)
        firebaseAuth = FirebaseAuth.getInstance()

        forgotPasswordText?.setOnClickListener{
            Log.d("tag", "account password reset activity intent")
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) //TODO Доделать активити сброса пароля
        }
        entryButton?.setOnClickListener{
            Log.d("tag", "Enter account activity intent")

            if (!TextUtils.isEmpty(loginTextFiled!!.text) && !TextUtils.isEmpty(passwordTextFiled!!.text)) {

                try {
                    signIn()
                } catch (e: Exception) {

                }
            }

        }
        registerText?.setOnClickListener{
            Log.d("tag", "Register activity intent")
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            this.finish()
        }
        loginTextFiled?.setOnClickListener{
            wrongUsernameOrPassword?.visibility = TextView.INVISIBLE
        }
        passwordTextFiled?.setOnClickListener{
            wrongUsernameOrPassword?.visibility = TextView.INVISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = firebaseAuth?.currentUser
        if (currentUser != null) {
            var start = startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }

    private fun signIn() {
        firebaseAuth?.signInWithEmailAndPassword(loginTextFiled?.text.toString(), passwordTextFiled?.text.toString())
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("tag", "signInWithEmail:success")
                    val user = firebaseAuth!!.currentUser
                    updateUI(user)
                    this.finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("tag", "signInWithEmail:failure", task.exception)
                    wrongUsernameOrPassword?.visibility = TextView.VISIBLE
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
    }


}
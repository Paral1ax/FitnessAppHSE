package com.mir.fitnessapplication.entry.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.SendAuthData
import com.mir.fitnessapplication.entry.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity(), SendAuthData{

    var loginTextFiled: EditText? = null
    var passwordTextFiled: EditText? = null
    var forgotPasswordText: TextView? = null
    var entryButton: Button? = null
    var registerText: TextView? = null
    private var wrongUsernameOrPassword: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginTextFiled = findViewById(R.id.editTextTextEmailAddress)
        passwordTextFiled = findViewById(R.id.editTextTextPassword)
        forgotPasswordText = findViewById(R.id.forgotPasswordText)
        entryButton = findViewById(R.id.loginEnterButton)
        registerText = findViewById(R.id.loginRegisterText)
        wrongUsernameOrPassword = findViewById(R.id.wrongUsernameOrPassword)

        forgotPasswordText?.setOnClickListener{
            Log.d("tag", "account password reset activity intent")
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) //TODO Доделать активити сброса пароля
        }
        entryButton?.setOnClickListener{
            Log.d("tag", "Enter account activity intent")
            if (sendAndCheckAuthData())
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) //TODO Доделать когда доделаю авторизацию
            else {
                wrongUsernameOrPassword?.visibility = TextView.VISIBLE
            }

        }
        registerText?.setOnClickListener{
            Log.d("tag", "Register activity intent")
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        loginTextFiled?.setOnClickListener{
            wrongUsernameOrPassword?.visibility = TextView.INVISIBLE
        }
        passwordTextFiled?.setOnClickListener{
            wrongUsernameOrPassword?.visibility = TextView.INVISIBLE
        }
    }

    override fun sendAndCheckAuthData(): Boolean {
        return true
    }
}
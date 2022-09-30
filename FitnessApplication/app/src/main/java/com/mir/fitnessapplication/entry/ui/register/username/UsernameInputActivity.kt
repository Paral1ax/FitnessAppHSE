package com.mir.fitnessapplication.entry.ui.register.username

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.register.RegisterActivity
import com.mir.fitnessapplication.entry.ui.register.data.DataActivity

class UsernameInputActivity : AppCompatActivity() {

    var email: EditText
    var password: EditText
    var registerButton: Button
    var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username_input)
        registerButton.setOnClickListener {
            register()
        }
    }

    init {
        email = findViewById(R.id.email_register_textfield)
        password = findViewById(R.id.registerPasswordText)
        registerButton = findViewById(R.id.registerUserButton)
        auth = FirebaseAuth.getInstance()
    }

    private fun register() {
        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("tag", "createUserWithEmail:success")
                    auth.currentUser?.sendEmailVerification()
                    startActivity(Intent(this@UsernameInputActivity, DataActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("tag", "createUserWithEmail:failure", task.exception)
                }
            }
    }
}
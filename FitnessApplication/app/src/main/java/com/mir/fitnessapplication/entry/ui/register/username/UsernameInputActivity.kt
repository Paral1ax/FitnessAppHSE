package com.mir.fitnessapplication.entry.ui.register.username

import android.content.Intent
import android.opengl.Visibility
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
import com.mir.fitnessapplication.main.MainActivity

class UsernameInputActivity : AppCompatActivity() {

    var email: EditText? = null
    var password: EditText? = null
    var registerButton: Button? = null
    var auth: FirebaseAuth? = null
    var taken: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username_input)

        email = findViewById(R.id.email_register_textfield)
        password = findViewById(R.id.registerPasswordText)
        registerButton = findViewById(R.id.registerUserButton)
        auth = FirebaseAuth.getInstance()
        taken = findViewById(R.id.thisUsernameIsTakenError)
        registerButton?.setOnClickListener {
            register()
        }
    }

    private fun register() {
        auth?.createUserWithEmailAndPassword(email!!.text.toString(), password!!.text.toString())?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("tag", "createUserWithEmail:success")
                    val user = auth?.currentUser
                    startActivity(Intent(this@UsernameInputActivity, DataActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("tag", "createUserWithEmail:failure", task.exception)
                    taken?.visibility = TextView.VISIBLE
                }
            }
    }
}
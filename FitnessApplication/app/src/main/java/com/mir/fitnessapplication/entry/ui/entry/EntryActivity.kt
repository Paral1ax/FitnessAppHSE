package com.mir.fitnessapplication.entry.ui.entry

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.login.LoginActivity
import com.mir.fitnessapplication.entry.ui.register.RegisterActivity

class EntryActivity : AppCompatActivity() {

    var createAccButton: Button? = null
    var enterAccButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        createAccButton = findViewById(R.id.createAccButton)
        enterAccButton = findViewById(R.id.enterButton)

        createAccButton?.setOnClickListener {
            Log.d("tag", "create account activity intent")
            startActivity(Intent(this@EntryActivity, RegisterActivity::class.java))
        }
        enterAccButton?.setOnClickListener{
            Log.d("tag", "enter account activity intent")
            startActivity(Intent(this@EntryActivity, LoginActivity::class.java))
        }
    }
}
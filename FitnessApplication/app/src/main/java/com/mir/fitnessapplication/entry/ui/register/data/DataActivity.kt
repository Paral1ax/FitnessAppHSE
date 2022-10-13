package com.mir.fitnessapplication.entry.ui.register.data

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.main.MainActivity


class DataActivity : AppCompatActivity(){

    val gdError = GradientDrawable()
    val gdRight = GradientDrawable()
    var username: EditText? = null
    var nameAndSurnameText: EditText? = null
    var dateOfBirth: EditText? = null
    var incorrectInput: TextView? = null
    var goAheadButton: Button? = null
    var goToLoginText: TextView? = null
    var auth: FirebaseAuth? = null
    var database: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        username = findViewById(R.id.username_data_textfield)
        nameAndSurnameText = findViewById(R.id.nameAndSurnameText)
        incorrectInput = findViewById(R.id.incorrectDataInput)
        goAheadButton = findViewById(R.id.go_nextRegisterButton)
        goToLoginText = findViewById(R.id.loginRegisterTextButton)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance(FirebaseURL.DATABASE_URL)
        databaseReference = database!!.getReference("UserData")

        gdError.setStroke(1, resources.getColor(R.color.error_color,null))
        gdRight.setStroke(1, resources.getColor(R.color.grey,null))

        nameAndSurnameText?.setOnClickListener {
            nameAndSurnameText?.setBackgroundDrawable(gdRight)
        }
        dateOfBirth?.setOnClickListener {
            dateOfBirth?.setBackgroundDrawable(gdRight)
        }
        goAheadButton?.setOnClickListener {
            addUserData()
        }
        goToLoginText?.setOnClickListener {

        }
    }

    private fun addUserData() {
        if (auth?.currentUser != null){

            if (username?.text!!.isNotEmpty() && nameAndSurnameText?.text!!.length > 5) {
                val data = UserData(username!!.text.toString(), nameAndSurnameText!!.text.toString())
                database?.getReference("UserData")
                    ?.child(FirebaseAuth.getInstance().currentUser!!.uid)!!.setValue(data)
                    .addOnCompleteListener {
                        Toast.makeText(this@DataActivity, "Successful Registered", Toast.LENGTH_SHORT).show()
                        print("Show")
                    }
                val intent = Intent(this@DataActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else incorrectInput?.visibility =TextView.INVISIBLE

        } else {

        }

    }
}
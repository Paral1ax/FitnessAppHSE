package com.mir.fitnessapplication.entry.ui.register.data

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.SendAuthData
import com.mir.fitnessapplication.entry.ui.login.DataPreprocessing
import com.mir.fitnessapplication.entry.ui.login.LoginActivity
import com.mir.fitnessapplication.entry.ui.register.username.UsernameInputActivity


class DataActivity : AppCompatActivity(), DataPreprocessing, SendAuthData {

    val gdError = GradientDrawable()
    val gdRight = GradientDrawable()
    var nameAndSurnameText: EditText? = null
    var dateOfBirth: EditText? = null
    var heightParam: EditText? = null
    var weightParam: EditText? = null
    var incorrectInput: TextView? = null
    var goAheadButton: Button? = null
    var goToLoginText: TextView? = null
    var gender: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        nameAndSurnameText = findViewById(R.id.nameAndSurnameText)
        dateOfBirth = findViewById(R.id.birthdateRegisterInput)
        heightParam = findViewById(R.id.heightRegisterInput)
        weightParam = findViewById(R.id.weightRegisterInput)
        incorrectInput = findViewById(R.id.incorrectDataInput)
        goAheadButton = findViewById(R.id.go_nextRegisterButton)
        goToLoginText = findViewById(R.id.loginRegisterTextButton)
        gender = findViewById(R.id.gender)
        gdError.setStroke(1, resources.getColor(R.color.error_color,null))
        gdRight.setStroke(1, resources.getColor(R.color.grey,null))

        nameAndSurnameText?.setOnClickListener {
            nameAndSurnameText?.setBackgroundDrawable(gdRight)
        }
        dateOfBirth?.setOnClickListener {
            dateOfBirth?.setBackgroundDrawable(gdRight)
        }
        heightParam?.setOnClickListener {
            heightParam?.setBackgroundDrawable(gdRight)
        }
        weightParam?.setOnClickListener {
            weightParam?.setBackgroundDrawable(gdRight)
        }
        gender?.setOnClickListener {
            gender?.setBackgroundDrawable(gdRight)
        }
        goAheadButton?.setOnClickListener {
            if (!preprocessing()) {
                incorrectInput?.visibility = View.VISIBLE
            }
            else if (sendAndCheckAuthData()) {
                startActivity(Intent(this@DataActivity, UsernameInputActivity::class.java))
            }
        }
        goToLoginText?.setOnClickListener {

        }
    }

    override fun sendAndCheckAuthData(): Boolean {
        TODO("Not yet implemented")
    }

    override fun preprocessing(): Boolean {
        incorrectInput?.visibility = View.INVISIBLE
        var exit: Boolean = true
        val splitName = nameAndSurnameText!!.text.split(" ")
        if (splitName.size < 2) {  //todo проверка на спец символы
            nameAndSurnameText?.setBackgroundDrawable(gdError)
            exit = false
        }
        if (dateOfBirth?.text!!.length < 4) {
            dateOfBirth?.setBackgroundDrawable(gdError)
            exit = false
        }
        if (heightParam?.text!!.length != 3) {
            heightParam?.setBackgroundDrawable(gdError)
            exit = false
        }
        if (weightParam?.text!!.length < 2 || weightParam?.text!!.length > 3) {
            weightParam?.setBackgroundDrawable(gdError)
            exit = false
        }
        //Todo проверка ресайклер вью

        return exit
    }
}
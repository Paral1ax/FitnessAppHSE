package com.mir.fitnessapplication.entry.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.hbb20.CountryCodePicker
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.SendAuthData
import com.mir.fitnessapplication.entry.ui.login.DataPreprocessing
import com.mir.fitnessapplication.entry.ui.login.LoginActivity
import com.mir.fitnessapplication.entry.ui.register.code.EnterCodeActivity

class RegisterActivity : AppCompatActivity(), SendAuthData, DataPreprocessing {

    var ccp: CountryCodePicker? = null
    var getPhoneNumberText: EditText? = null
    var incorrectNumberInput: TextView? = null
    var registerGoesAhead: Button? = null
    private var loginTextField: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ccp = findViewById(R.id.ccp)
        getPhoneNumberText = findViewById(R.id.getPhoneNumber)
        incorrectNumberInput = findViewById(R.id.incorrectNumberInput)
        registerGoesAhead = findViewById(R.id.registerGoesAhead)
        loginTextField = findViewById(R.id.loginRegisterClickableText)
        ccp?.registerCarrierNumberEditText(getPhoneNumberText)

        registerGoesAhead?.setOnClickListener{
            if (preprocessing()) {

            }
            else incorrectNumberInput?.visibility = TextView.VISIBLE
        }

        ccp?.setOnClickListener{
            incorrectNumberInput?.visibility = TextView.INVISIBLE
        }
        getPhoneNumberText?.setOnClickListener{
            incorrectNumberInput?.visibility = TextView.INVISIBLE
        }
        loginTextField?.setOnClickListener{
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    override fun sendAndCheckAuthData(): Boolean {
        ccp?.fullNumberWithPlus;

        startActivity(Intent(this@RegisterActivity, EnterCodeActivity::class.java))
        return true
    }

    override fun preprocessing(): Boolean {
        return ccp?.isValidFullNumber == true
    }
}
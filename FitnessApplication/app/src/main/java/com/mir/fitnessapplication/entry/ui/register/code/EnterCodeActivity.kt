package com.mir.fitnessapplication.entry.ui.register.code

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.mir.fitnessapplication.R
import com.mir.fitnessapplication.entry.ui.SendAuthData
import com.mir.fitnessapplication.entry.ui.register.RegisterActivity
import com.mir.fitnessapplication.entry.ui.register.data.DataActivity

class EnterCodeActivity : AppCompatActivity(), SendAuthData {

    var editCodeTextField: EditText? = null
    var goAheadButton: Button? = null
    var sendSmsAgain: TextView? = null
    var changePhoneNumberText: TextView? = null
    var incorrectAuthCode: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_code)

        editCodeTextField = findViewById(R.id.editTextNumber)
        goAheadButton = findViewById(R.id.enterCodeGoAheadButton)
        sendSmsAgain = findViewById(R.id.sendSmsAgainText)
        changePhoneNumberText = findViewById(R.id.changePhoneNumberText)
        incorrectAuthCode = findViewById(R.id.confirmationCodeIncorrect)

        goAheadButton?.setOnClickListener{  //обработка кнопки далее
            if (sendAndCheckAuthData())
                startActivity(Intent(this@EnterCodeActivity, DataActivity::class.java))
            else {
                val gradientDrawable: GradientDrawable = editCodeTextField?.background as GradientDrawable
                gradientDrawable.setStroke(2,Color.RED)
                incorrectAuthCode?.visibility = TextView.VISIBLE
            }
        }
        editCodeTextField?.setOnClickListener{
            incorrectAuthCode?.visibility = TextView.INVISIBLE
        }
        sendSmsAgain?.setOnClickListener{
            //todo отправка кода заново
        }
        changePhoneNumberText?.setOnClickListener{
            startActivity(Intent(this@EnterCodeActivity, RegisterActivity::class.java))
        }
    }

    override fun sendAndCheckAuthData(): Boolean {
        TODO("Проверка кода авторизации")
    }
}
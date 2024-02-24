package com.example.tms_anonl_17_lesson_18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    //private lateinit var button: Button
    private lateinit var password: TextInputEditText
    private lateinit var login: TextInputEditText
    private lateinit var optionOne: CheckBox
    private lateinit var optionTwo: CheckBox
    private lateinit var optionThree: CheckBox
    private lateinit var radioOne: RadioButton
    private lateinit var radioTwo: RadioButton
    private lateinit var radioThree: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()

        findViewById<Button>(R.id.button).setOnClickListener {
            val userParam = initUserParam()
            startActivity(UserActivity.launchIntent(this, userParam))
        }
    }

    private fun initUserParam(): User {
        return User(
            login = login.text.toString(),
            password = password.text.toString(),
            color = checkColor(),
            options = checkOptions()
        )
    }

    private fun initViews() {
        login = findViewById(R.id.id_login)
        password = findViewById(R.id.id_password)

        radioOne = findViewById(R.id.rb_one)
        radioTwo = findViewById(R.id.rb_two)
        radioThree = findViewById(R.id.rb_three)

        optionOne = findViewById(R.id.cb_notif)
        optionTwo = findViewById(R.id.cb_offers)
        optionThree = findViewById(R.id.cb_news)
    }

    private fun checkColor(): String {
        val result = when {
            radioOne.isChecked -> radioOne.text
            radioTwo.isChecked -> radioTwo.text
            radioThree.isChecked -> radioThree.text
            else -> ""
        }
        return result.toString()
    }

    private fun checkOptions(): List<String> {
        val result = mutableListOf<String>()

        if(optionOne.isChecked){
            result.add(optionOne.text.toString())
        }else{
            result.add("")
        }

        if(optionTwo.isChecked){
            result.add(optionTwo.text.toString())
        } else{
            result.add("")
        }

        if(optionThree.isChecked){
            result.add(optionThree.text.toString())
        }else{
            result.add("")
        }
//        result.add(optionTwo.text.toString())
//        result.add(optionThree.text.toString())

        return result
    }
}
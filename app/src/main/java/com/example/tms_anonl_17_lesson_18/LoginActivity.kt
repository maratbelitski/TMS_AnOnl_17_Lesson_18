package com.example.tms_anonl_17_lesson_18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    companion object {
        const val IS_EMPTY = ""
    }

    private lateinit var password: TextInputEditText
    private lateinit var login: TextInputEditText
    private lateinit var optionOne: CheckBox
    private lateinit var optionTwo: CheckBox
    private lateinit var optionThree: CheckBox
    private lateinit var radioOne: RadioButton
    private lateinit var radioTwo: RadioButton
    private lateinit var radioThree: RadioButton
    private lateinit var logInButton: Button
    private lateinit var textWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()


        login.addTextChangedListener(textWatcher)
        password.addTextChangedListener(textWatcher)

        logInButton.setOnClickListener {
            val userParam = initUserParam()
            startActivity(UserActivity.launchIntent(this, userParam))
        }
    }

    private fun initUserParam(): User {
        return User(
            login = login.text.toString().trim(),
            password = password.text.toString().trim(),
            color = checkColor(),
            options = checkOptions()
        )
    }

    private fun initViews() {
        logInButton = findViewById(R.id.button)
        logInButton.isEnabled = false

        login = findViewById(R.id.id_login)
        password = findViewById(R.id.id_password)

        radioOne = findViewById(R.id.rb_one)
        radioTwo = findViewById(R.id.rb_two)
        radioThree = findViewById(R.id.rb_three)

        optionOne = findViewById(R.id.cb_notif)
        optionTwo = findViewById(R.id.cb_offers)
        optionThree = findViewById(R.id.cb_news)

        textWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val log = login.text?.toString()?.trim()
                val pass = password.text?.toString()?.trim()

                logInButton.isEnabled = log!!.length > 5 && pass!!.length > 5

                if (log.length > 5 && pass!!.length > 5){
                    logInButton.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
    }

    private fun checkColor(): String {
        val result = when {
            radioOne.isChecked -> radioOne.text
            radioTwo.isChecked -> radioTwo.text
            radioThree.isChecked -> radioThree.text
            else -> IS_EMPTY
        }
        return result.toString()
    }

    private fun checkOptions(): List<String> {
        val result = mutableListOf<String>()

        if (optionOne.isChecked) {
            result.add(optionOne.text.toString())
        } else {
            result.add(IS_EMPTY)
        }

        if (optionTwo.isChecked) {
            result.add(optionTwo.text.toString())
        } else {
            result.add(IS_EMPTY)
        }

        if (optionThree.isChecked) {
            result.add(optionThree.text.toString())
        } else {
            result.add(IS_EMPTY)
        }
        return result
    }
}
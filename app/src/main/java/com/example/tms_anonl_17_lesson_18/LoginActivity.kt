package com.example.tms_anonl_17_lesson_18

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    companion object {
        const val IS_EMPTY = ""
    }

    private lateinit var password: TextInputEditText
    private lateinit var login: TextInputEditText
    private lateinit var textInputLayoutOne: TextInputLayout
    private lateinit var textInputLayoutTwo: TextInputLayout
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

    private fun initViews() {
        logInButton = findViewById(R.id.button)

        textInputLayoutOne = findViewById(R.id.textInputLayout)
        textInputLayoutTwo = findViewById(R.id.textInputLayout2)

        login = findViewById(R.id.id_login)
        password = findViewById(R.id.id_password)

        radioOne = findViewById(R.id.rb_one)
        radioTwo = findViewById(R.id.rb_two)
        radioThree = findViewById(R.id.rb_three)

        optionOne = findViewById(R.id.cb_notif)
        optionTwo = findViewById(R.id.cb_offers)
        optionThree = findViewById(R.id.cb_news)

        textWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validationEnable()
                validationError()
            }

            override fun afterTextChanged(s: Editable?) {}
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

    private fun validationEnable() {
        val logText = login.text?.toString()?.trim()
        val passText = password.text?.toString()?.trim()

        logInButton.isEnabled = logText!!.length > 5 && passText!!.length > 5

        if (logText.length > 5 && passText!!.length > 5) {
            logInButton.isEnabled = true
        }
    }

    private fun validationError() {
        val logText = login.text?.toString()?.trim()
        val passText = password.text?.toString()?.trim()

        if (logText != null) {
            if (logText.length < 5 && !login.isFocused) {
                textInputLayoutOne.isErrorEnabled = true
                textInputLayoutOne.error = resources.getString(R.string.error_message)
            } else {
                textInputLayoutOne.isErrorEnabled = false
            }
        }

        if (passText != null) {
            if (passText.length < 5 && !password.isFocused) {
                textInputLayoutTwo.isErrorEnabled = true
                textInputLayoutTwo.error = resources.getString(R.string.error_message)
            } else {
                textInputLayoutTwo.isErrorEnabled = false
            }
        }
    }
}
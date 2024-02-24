package com.example.tms_anonl_17_lesson_18

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi

class UserActivity : AppCompatActivity() {

    private lateinit var login: TextView
    private lateinit var password: TextView
    private lateinit var color: TextView
    private lateinit var optionOne: TextView
    private lateinit var optionTwo: TextView
    private lateinit var optionThree: TextView


    companion object {

        const val USER_OBJECT = "user_object"

        fun launchIntent(context: Context, userParam: User): Intent {
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra(USER_OBJECT, userParam)
            return intent
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val userParams = intent.getParcelableExtra(USER_OBJECT, User::class.java)

        initViews()
        changeParamViews(userParams)
        changeOptionsViews(userParams)
    }

    private fun changeParamViews(userParams: User?) {
        login.text = userParams?.login
        password.text = userParams?.password
        color.text = userParams?.color
        optionOne.text = userParams?.options?.get(0).toString()
        optionTwo.text = userParams?.options?.get(1).toString()
        optionThree.text = userParams?.options?.get(2).toString()
    }

    private fun changeOptionsViews(userParams: User?) {
        if (userParams?.options?.get(0)?.isBlank() == false){
            optionOne.visibility = View.VISIBLE
            optionOne.text = userParams.options[0]
        }

        if (userParams?.options?.get(1)?.isBlank() == false){
            optionTwo.visibility = View.VISIBLE
            optionTwo.text = userParams.options[1]
        }

        if (userParams?.options?.get(2)?.isBlank() == false){
            optionThree.visibility = View.VISIBLE
            optionThree.text = userParams.options[2]
        }
    }

    private fun initViews() {
        login = findViewById(R.id.tv_new_login)
        password = findViewById(R.id.tv_new_password)
        color = findViewById(R.id.tv_new_color)
        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
    }
}
package com.example.tms_anonl_17_lesson_18

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val login: String = "",
    val password: String = "",
    val color: String = "",
    val options: List<String>,
) : Parcelable

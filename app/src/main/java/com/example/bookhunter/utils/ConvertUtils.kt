package com.example.bookhunter.utils

import java.text.SimpleDateFormat
import java.util.*

fun isMaxResultsValid(stringNum: String): Boolean {
    when (stringNum.toIntOrNull()) {
        null -> return false
        else -> return stringNum.toInt() in 1..40
    }
}

fun currentDateAsString(): String {
    val sdf = SimpleDateFormat("'Date:' dd/MM/yyyy', time:' hh:mm:ss")
    return sdf.format(Date())
}
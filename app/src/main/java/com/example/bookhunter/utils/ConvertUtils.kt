package com.example.bookhunter.utils

import java.text.SimpleDateFormat
import java.util.*

fun isMaxResultsValid(stringNum: String): Boolean {
    when (stringNum.toIntOrNull()) {
        null -> return false
        else -> return stringNum.toInt() in 1..40
    }
}

// TODO: change current date getter
fun currentDateAsString(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    return sdf.format(Date())
}
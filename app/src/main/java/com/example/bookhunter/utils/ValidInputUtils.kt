package com.example.bookhunter.utils

fun isMaxResultsValid(stringNum: String): Boolean {
    when (stringNum.toIntOrNull()) {
        null -> return false
        else -> return stringNum.toInt() in 1..40
    }
}
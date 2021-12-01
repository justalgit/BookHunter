package com.example.bookhunter.utils

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Converters class for date storing in Room
 */

class RoomDateConverters {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

}

/**
 * Other data processing functions
 */

fun isMaxResultsValid(stringNum: String): Boolean {
    when (stringNum.toIntOrNull()) {
        null -> return false
        else -> return stringNum.toInt() in 1..40
    }
}

@SuppressLint("NewApi")
fun getStringFormattedDate(date: Date): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val instant = Instant.ofEpochMilli(date.time)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(date)
}
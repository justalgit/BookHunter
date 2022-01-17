package com.example.bookhunter.utils

import android.annotation.SuppressLint
import androidx.room.TypeConverter
//import java.time.Instant
//import java.time.LocalDateTime
//import java.time.ZoneId
//import java.time.format.DateTimeFormatter
import java.util.*
import org.threeten.bp.*
import org.threeten.bp.format.DateTimeFormatter

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

@SuppressLint("NewApi")
fun getStringFormattedDate(date: Date): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    val instant = Instant.ofEpochMilli(date.time)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return formatter.format(date)
}
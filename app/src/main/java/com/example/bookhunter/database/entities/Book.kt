package com.example.bookhunter.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    val id: String,
    val title: String?,
    val authors: List<String>?,
    val url: String?
) {

    val authorsStringFormatted
        get() = authors?.joinToString { it }
}
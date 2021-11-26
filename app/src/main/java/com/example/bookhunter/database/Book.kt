package com.example.bookhunter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    var id: String,
    var title: String? = "No title",
    var authors: String? = "No authors",
    var url: String? = "No url",
    var savingDate: String? = "No saving date"
)
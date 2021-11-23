package com.example.bookhunter.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class HistoryRecord (
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val date: Date,
    val searchQuery: String,
    val maxResult: Int)
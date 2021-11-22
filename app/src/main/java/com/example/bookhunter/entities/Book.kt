package com.example.bookhunter.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book (
    @PrimaryKey
    val id: String,
    val title: String,
    val authors: List<String>,
    val canonicalVolumeLink: String) {

    val authorsToString: String
        get() = authors.joinToString { it }

}
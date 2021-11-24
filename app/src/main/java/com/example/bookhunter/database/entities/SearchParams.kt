package com.example.bookhunter.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "search_params")
@Parcelize
data class SearchParams(
    val searchQuery: String?,
    val maxResults: Int?,
    val date: Date = Calendar.getInstance().time,
    @PrimaryKey(autoGenerate = true)
    val id: String = "-1"
) : Parcelable
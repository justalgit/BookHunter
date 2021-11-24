package com.example.bookhunter.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlinx.android.parcel.Parcelize

@Parcelize
//@Entity(tableName = "search_params")
data class SearchParams(
    val searchQuery: String?,
    val maxResults: Int?,
    val date: String = Calendar.getInstance().time.toString(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1
) : Parcelable
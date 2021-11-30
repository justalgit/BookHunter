package com.example.bookhunter.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "search_params")
data class SearchParams(
    var searchQuery: String?,
    var maxResults: Int?,
    // TODO: change the format of date storing
    var date: String?,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
) : Parcelable
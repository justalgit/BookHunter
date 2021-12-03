package com.example.bookhunter.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bookhunter.utils.RoomDateConverters
import com.example.bookhunter.utils.getStringFormattedDate
import java.util.*
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Parcelize
@Entity(tableName = "search_params")
data class SearchParams(
    var searchQuery: String?,
    @TypeConverters(RoomDateConverters::class)
    var date: Date? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
) : Parcelable {

    fun dateAsString(): String {
        return getStringFormattedDate(date!!)
    }
}
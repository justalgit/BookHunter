package com.example.bookhunter.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.bookhunter.utils.RoomDateConverters
import com.example.bookhunter.utils.getStringFormattedDate
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    var id: String,
    var title: String?,
    var authors: String?,
    var url: String?,
    @TypeConverters(RoomDateConverters::class)
    var savingDate: Date? = null,
    var note: String? = null
) : Parcelable {

    fun dateAsString(): String {
        return getStringFormattedDate(savingDate!!)
    }

}
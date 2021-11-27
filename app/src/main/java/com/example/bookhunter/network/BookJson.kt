package com.example.bookhunter.network

import com.example.bookhunter.database.Book
import com.squareup.moshi.JsonClass

const val NO_INFO = "no info"

@JsonClass(generateAdapter = true)
data class BookItems(
    val items: List<BookInfo>? = null,
    val totalItems: Int? = null)

@JsonClass(generateAdapter = true)
data class BookInfo(
    val id: String,
    val volumeInfo: VolumeInfo)

@JsonClass(generateAdapter = true)
data class VolumeInfo(
    val title: String? = NO_INFO,
    val authors: List<String>? = listOf(NO_INFO),
    val canonicalVolumeLink: String? = NO_INFO)


/**
 * Convert Network results to database objects
 */
fun BookItems.asDatabaseModel(): List<Book>? {
    return items?.map {
        Book(
            id = it.id,
            title = it.volumeInfo.title,
            authors = it.volumeInfo.authors?.joinToString { it },
            url = it.volumeInfo.canonicalVolumeLink
        )
    }
}
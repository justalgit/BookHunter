package com.example.bookhunter.network

import com.example.bookhunter.database.Book
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookItems(
    val items: List<BookInfo>? = null,
    val totalItems: Int? = null)

@JsonClass(generateAdapter = true)
data class BookInfo(
    val id: String,
    val volumeInfo: VolumeInfo? = null)

@JsonClass(generateAdapter = true)
data class VolumeInfo(
    val title: String? = null,
    val authors: List<String>? = null,
    val canonicalVolumeLink: String? = null)


/**
 * Convert Network results to database objects
 */
fun BookItems.asDatabaseModel(): List<Book>? {
    return items?.map {
        Book(
            id = it.id,
            title = it.volumeInfo?.title,
            authors = it.volumeInfo?.authors?.joinToString { it },
            url = it.volumeInfo?.canonicalVolumeLink)
    }
}
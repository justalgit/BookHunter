package com.example.bookhunter.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Base URL for Google Books API
private const val BASE_URL =  "https://www.googleapis.com/books/v1/"
// Parameter for the search string
private const val QUERY_PARAM = "q"
// Parameter that limits search results
private const val MAX_RESULTS = "maxResults"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface BooksApiService {

    @GET("volumes")
    suspend fun getBooks(
        @Query(QUERY_PARAM) q: String?,
        @Query(MAX_RESULTS) maxResults: Int?
    ): BookItems

}


object BooksApi {
    val retrofitService : BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}
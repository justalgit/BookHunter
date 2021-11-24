package com.example.bookhunter.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookhunter.database.entities.SearchParams

interface SearchParamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchParams: SearchParams)

    @Query("select * from search_params")
    fun getAll(): LiveData<List<SearchParams>>

}
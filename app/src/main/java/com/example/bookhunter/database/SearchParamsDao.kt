package com.example.bookhunter.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchParamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchParams: SearchParams)

    @Query("select * from search_params order by date desc")
    fun getAll(): LiveData<List<SearchParams>>

    @Query("delete from search_params")
    fun deleteAll()
}
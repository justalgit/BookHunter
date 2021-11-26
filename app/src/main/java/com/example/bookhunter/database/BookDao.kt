package com.example.bookhunter.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Query("select * from books order by savingDate desc")
    fun getAllSortedByDate(): LiveData<List<Book>>

    @Query("select * from books order by title")
    fun getAllSortedByTitle(): LiveData<List<Book>>

    @Query("delete from books")
    fun deleteAll()
}
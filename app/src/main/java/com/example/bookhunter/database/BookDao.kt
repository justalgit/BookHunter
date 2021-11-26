package com.example.bookhunter.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)

    @Query("select * from books order by savingDate desc")
    fun getAllSortedByDate(): LiveData<List<Book>>

    @Query("select * from books order by title")
    fun getAllSortedByTitle(): LiveData<List<Book>>

    @Query("delete from books")
    fun deleteAll()
}
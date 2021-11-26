package com.example.bookhunter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class, SearchParams::class], version = 5, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {

    abstract val bookDao: BookDao
    abstract val searchParamsDao: SearchParamsDao

    companion object {

        @Volatile
        private var INSTANCE: BooksDatabase? = null

        fun getInstance(context: Context): BooksDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BooksDatabase::class.java,
                        "main_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
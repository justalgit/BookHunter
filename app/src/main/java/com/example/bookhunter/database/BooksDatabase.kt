package com.example.bookhunter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookhunter.utils.RoomDateConverters

@Database(entities = [Book::class, SearchParams::class], version = 6, exportSchema = false)
@TypeConverters(RoomDateConverters::class)
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
package com.example.bookhunter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookhunter.database.entities.Book
import com.example.bookhunter.database.entities.SearchParams

@Database(entities = [Book::class, SearchParams::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val bookDao: BookDao
    abstract val searchParamsDao: SearchParamsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
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
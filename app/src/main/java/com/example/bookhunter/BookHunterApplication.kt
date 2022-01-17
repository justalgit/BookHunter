package com.example.bookhunter

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class BookHunterApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}
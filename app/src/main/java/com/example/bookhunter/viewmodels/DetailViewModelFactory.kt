package com.example.bookhunter.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookhunter.database.Book

class DetailViewModelFactory(
    private val selectedBook: Book,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(selectedBook, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
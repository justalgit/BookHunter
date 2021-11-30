package com.example.bookhunter.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.bookhunter.database.BooksDatabase
import com.example.bookhunter.database.BooksRepository
import kotlinx.coroutines.launch

class HistoryViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val booksRepository = BooksRepository(BooksDatabase.getInstance(application))

    val history = booksRepository.getHistory()

    fun clearHistory() {
        viewModelScope.launch {
            booksRepository.clearHistory()
        }
    }
}
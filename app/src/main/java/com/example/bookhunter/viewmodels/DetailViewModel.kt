package com.example.bookhunter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookhunter.database.Book
import com.example.bookhunter.database.BooksDatabase
import com.example.bookhunter.database.BooksRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    selectedBook: Book,
    application: Application
) : AndroidViewModel(application) {

    private val booksRepository = BooksRepository(BooksDatabase.getInstance(application))

    private val _selectedBook = MutableLiveData<Book>()
    val selectedBook: LiveData<Book>
        get() = _selectedBook

    private val _isNavigatingToOverview = MutableLiveData<Boolean>()
    val isNavigatingToOverview: LiveData<Boolean>
        get() = _isNavigatingToOverview

    private val _isBookUpdated = MutableLiveData<Boolean>()
    val isBookUpdated: LiveData<Boolean>
        get() = _isBookUpdated

    private val _isBookDeleted = MutableLiveData<Boolean>()
    val isBookDeleted: LiveData<Boolean>
        get() = _isBookDeleted


    init {
        _selectedBook.value = selectedBook
    }


    fun updateBook(book: Book) {
        viewModelScope.launch {
            booksRepository.updateBook(book)
        }
        _isBookUpdated.value = true
        navigateToOverview()
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            booksRepository.deleteBook(book)
        }
        _isBookDeleted.value = true
        navigateToOverview()
    }


    fun navigateToOverview() {
        _isNavigatingToOverview.value = true
    }

    fun navigateToOverviewDone() {
        _isNavigatingToOverview.value = false
        _isBookDeleted.value = false
        _isBookUpdated.value = false
    }
}
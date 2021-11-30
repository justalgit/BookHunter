package com.example.bookhunter.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.bookhunter.database.Book
import com.example.bookhunter.database.BooksDatabase
import com.example.bookhunter.database.BooksRepository
import kotlinx.coroutines.launch

class OverviewViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val booksRepository = BooksRepository(BooksDatabase.getInstance(application))

    private val _savedBooks = MediatorLiveData<List<Book>>()
    val savedBooks: LiveData<List<Book>>
        get() = _savedBooks

    private val _isNavigatingToSearch = MutableLiveData<Boolean>()
    val isNavigatingToSearch: LiveData<Boolean>
        get() = _isNavigatingToSearch

    private val _isNavigatingToHistory = MutableLiveData<Boolean>()
    val isNavigatingToHistory: LiveData<Boolean>
        get() = _isNavigatingToHistory

    private val _isNavigatingToAbout = MutableLiveData<Boolean>()
    val isNavigatingToAbout: LiveData<Boolean>
        get() = _isNavigatingToAbout


    private val _isSortedByBookDate = MutableLiveData<Boolean>()
    val isSortedByBookDate: LiveData<Boolean>
        get() = _isSortedByBookDate

    init {
        _savedBooks.addSource(booksRepository.getBooksOrderedByDate()) {
            _savedBooks.value = it
        }
        _isSortedByBookDate.value = true
    }


    fun sortBooksByName(): Boolean {
        if (isSortedByBookDate.value == true) {
            _savedBooks.addSource(booksRepository.getBooksOrderedByTitle()) {
                _savedBooks.value = it
            }
            _isSortedByBookDate.value = false
            return true
        }
        return false
    }


    fun sortBooksByDate(): Boolean {
        if (isSortedByBookDate.value == false) {
            _savedBooks.addSource(booksRepository.getBooksOrderedByDate()) {
                _savedBooks.value = it
            }
            _savedBooks.value = savedBooks.value
            _isSortedByBookDate.value = true
            return true
        }
        return false
    }


    fun clearSavedBooks() {
        viewModelScope.launch {
            booksRepository.clearSavedBooks()
        }
    }

    /**
     * Navigation to search fragment
     */

    fun navigateToSearch() {
        _isNavigatingToSearch.value = true
    }

    fun navigateToSearchDone() {
        _isNavigatingToSearch.value = false
    }

    /**
     * Navigation to history fragment
     */

    fun navigateToHistory() {
        _isNavigatingToHistory.value = true
    }

    fun navigateToHistoryDone() {
        _isNavigatingToHistory.value = false
    }

    /**
     * Navigation to "about" fragment
     */

    fun navigateToAbout() {
        _isNavigatingToAbout.value = true
    }

    fun navigateToAboutDone() {
        _isNavigatingToAbout.value = false
    }
}
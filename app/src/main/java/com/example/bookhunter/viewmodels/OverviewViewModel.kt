package com.example.bookhunter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookhunter.database.BooksDatabase
import com.example.bookhunter.database.BooksRepository

class OverviewViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val booksRepository = BooksRepository(BooksDatabase.getInstance(application))

    val savedBooks = booksRepository.savedBooks

    private val _isNavigatingToSearch = MutableLiveData<Boolean>()
    val isNavigatingToSearch: LiveData<Boolean>
        get() = _isNavigatingToSearch

    private val _isNavigatingToHistory = MutableLiveData<Boolean>()
    val isNavigatingToHistory: LiveData<Boolean>
        get() = _isNavigatingToHistory

    //private val _displayBookDetail = MutableLiveData<Book>()
    //val displayBookDetail: LiveData<Book>
    //    get() = _displayBookDetail

    private val _isNavigatingToAbout = MutableLiveData<Boolean>()
    val isNavigatingToAbout: LiveData<Boolean>
        get() = _isNavigatingToAbout

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
package com.example.bookhunter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OverviewViewModel : ViewModel() {

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
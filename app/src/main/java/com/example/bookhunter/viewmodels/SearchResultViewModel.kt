package com.example.bookhunter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchResultViewModel : ViewModel() {

    private val _isNavigatingToOverview = MutableLiveData<Boolean>()
    val isNavigatingToOverview: LiveData<Boolean>
        get() = _isNavigatingToOverview

    fun navigateToOverview() {
        _isNavigatingToOverview.value = true
    }

    fun navigateToOverviewDone() {
        _isNavigatingToOverview.value = null
    }

}
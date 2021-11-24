package com.example.bookhunter.viewmodels

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    val searchQuery = MutableLiveData<String>()
    val maxResults = MutableLiveData<String>()

    private val _isNavigatingToResult = MutableLiveData<Boolean>()
    val isNavigatingToResult: LiveData<Boolean>
        get() = _isNavigatingToResult

    fun navigateToResult() {
        _isNavigatingToResult.value = true
        Log.d("SearchViewModel", "Search parameters: ${searchQuery.value}, ${maxResults.value}")
    }

    fun navigateToResultDone() {
        _isNavigatingToResult.value = false
    }

}
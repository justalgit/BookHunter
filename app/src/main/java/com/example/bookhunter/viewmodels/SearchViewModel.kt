package com.example.bookhunter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _isNavigatingToResult = MutableLiveData<Boolean>()
    val isNavigatingToResult: LiveData<Boolean>
        get() = _isNavigatingToResult

    fun navigateToResult() {
        _isNavigatingToResult.value = true
    }

    fun navigateToResultDone() {
        _isNavigatingToResult.value = null
    }

}
package com.example.bookhunter.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookhunter.database.SearchParams
import java.util.*

class SearchViewModel() : ViewModel() {

    val searchQuery = MutableLiveData<String>()

    private val _isNavigatingToResult = MutableLiveData<SearchParams>()
    val isNavigatingToResult: LiveData<SearchParams>
        get() = _isNavigatingToResult

    private val _isInputValid = MutableLiveData<Boolean>()
    val isInputValid: LiveData<Boolean>
        get() = _isInputValid


    fun navigateToResult() {

        _isInputValid.value = true

        Log.d("SearchViewModel", "Query value: ${searchQuery.value}")
        if (searchQuery.value == null || searchQuery.value?.isEmpty() == true) {
            _isInputValid.value = false
        }
        else {
            val currentSearchParams = SearchParams(
                searchQuery.value,
                Calendar.getInstance().time
            )
            _isNavigatingToResult.value = currentSearchParams
        }

    }


    fun navigateToResultDone() {
        _isNavigatingToResult.value = null
    }

}
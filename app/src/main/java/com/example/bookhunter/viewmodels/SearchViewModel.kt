package com.example.bookhunter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookhunter.database.SearchParams
import com.example.bookhunter.utils.isMaxResultsValid
import java.util.*

class SearchViewModel() : ViewModel() {

    val searchQuery = MutableLiveData<String>()
    val maxResults = MutableLiveData<String>()

    private val _isNavigatingToResult = MutableLiveData<SearchParams>()
    val isNavigatingToResult: LiveData<SearchParams>
        get() = _isNavigatingToResult

    private val _isInputValid = MutableLiveData<Boolean>()
    val isInputValid: LiveData<Boolean>
        get() = _isInputValid


    fun navigateToResult() {

        _isInputValid.value = true

        if (searchQuery.value?.isEmpty() == true || !isMaxResultsValid(maxResults.value.toString())) {
            _isInputValid.value = false
        }
        else {
            val currentSearchParams = SearchParams(
                searchQuery.value,
                maxResults.value?.toInt(),
                Calendar.getInstance().time
            )

            _isNavigatingToResult.value = currentSearchParams
        }

    }


    fun navigateToResultDone() {
        _isNavigatingToResult.value = null
    }

}
package com.example.bookhunter.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookhunter.database.SearchParams

class SearchResultViewModelFactory(
    private val searchParams: SearchParams,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchResultViewModel::class.java)) {
            return SearchResultViewModel(searchParams, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
package com.example.bookhunter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchResultViewModelFactory(
    private val searchQuery: String,
    private val maxResults: String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchResultViewModel::class.java)) {
            return SearchResultViewModel(searchQuery, maxResults) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
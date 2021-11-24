package com.example.bookhunter.viewmodels

import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhunter.database.Book
import com.example.bookhunter.network.BooksApi
import com.example.bookhunter.network.asDatabaseModel
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchResultViewModel(searchQuery: String, maxResults: String) : ViewModel() {

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private val _maxResults = MutableLiveData<String>()
    val maxResults: LiveData<String>
        get() = _maxResults

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
        get() = _books

    private val _isNavigatingToOverview = MutableLiveData<Boolean>()
    val isNavigatingToOverview: LiveData<Boolean>
        get() = _isNavigatingToOverview


    init {
        _searchQuery.value = searchQuery
        _maxResults.value = maxResults
        getBooks(searchQuery, maxResults)
    }


    private fun getBooks(searchQuery: String, maxResults: String) {
        viewModelScope.launch {
            try {
                _books.value = BooksApi.retrofitService.getBooks(
                    searchQuery,
                    maxResults.toInt()
                ).asDatabaseModel()
                Log.d("SearchResultViewModel", "Fetched ${books.value?.size} values")
                Log.d("SearchResultViewModel", books.value.toString())
            }
            catch (e: Exception) {
                _books.value = ArrayList()
                Log.d("SearchResultViewModel", "Error while fetching books: ${e.message}")
            }
        }
    }


    fun navigateToOverview() {
        _isNavigatingToOverview.value = true
    }

    fun navigateToOverviewDone() {
        _isNavigatingToOverview.value = false
    }

}
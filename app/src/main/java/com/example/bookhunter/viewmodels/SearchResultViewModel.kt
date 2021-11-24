package com.example.bookhunter.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookhunter.database.SearchParamsDao
import com.example.bookhunter.database.entities.Book
import com.example.bookhunter.database.entities.SearchParams
import com.example.bookhunter.network.BooksApi
import com.example.bookhunter.network.asDatabaseModel
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchResultViewModel(
    dataSource: SearchParamsDao,
    searchParams: SearchParams
) : ViewModel() {

    private val _searchParams = MutableLiveData<SearchParams>()
    val searchParams: LiveData<SearchParams>
        get() = _searchParams

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
        get() = _books

    private val _isNavigatingToOverview = MutableLiveData<Boolean>()
    val isNavigatingToOverview: LiveData<Boolean>
        get() = _isNavigatingToOverview


    init {
        _searchParams.value = searchParams
        getBooks(searchParams)
    }


    private fun getBooks(searchParams: SearchParams) {
        viewModelScope.launch {
            try {
                _books.value = BooksApi.retrofitService.getBooks(
                    searchParams.searchQuery,
                    searchParams.maxResults
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
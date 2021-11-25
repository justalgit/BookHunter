package com.example.bookhunter.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.bookhunter.database.Book
import com.example.bookhunter.database.BooksDatabase
import com.example.bookhunter.database.BooksRepository
import com.example.bookhunter.database.SearchParams
import com.example.bookhunter.network.BooksApi
import com.example.bookhunter.network.asDatabaseModel
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchResultViewModel(
    searchParams: SearchParams,
    application: Application
) : AndroidViewModel(application) {

    private val booksRepository = BooksRepository(BooksDatabase.getInstance(application))

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
        saveToHistory(searchParams)
    }


    private fun getBooks(searchParams: SearchParams) {
        viewModelScope.launch {
            try {
                _books.value = booksRepository.getBooksFromApi(searchParams)
                Log.d(
                    "SearchResultViewModel",
                    "Current values in database: ${booksRepository.historySearchParams.value}"
                )
            }
            catch (e: Exception) {
                _books.value = ArrayList()
                Log.d("SearchResultViewModel", "Error while fetching books: ${e.message}")
            }
        }
    }

    fun saveBook(book: Book) {
        viewModelScope.launch {
            booksRepository.insertBook(book)
        }
    }

    private fun saveToHistory(searchParams: SearchParams) {
        viewModelScope.launch {
            booksRepository.insertSearchParams(searchParams)
        }
    }


    fun navigateToOverview() {
        _isNavigatingToOverview.value = true
    }

    fun navigateToOverviewDone() {
        _isNavigatingToOverview.value = false
    }

}
package com.example.bookhunter.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.bookhunter.database.Book
import com.example.bookhunter.database.BooksDatabase
import com.example.bookhunter.database.BooksRepository
import com.example.bookhunter.database.SearchParams
import com.example.bookhunter.utils.currentDateAsString
import kotlinx.coroutines.launch
import java.lang.Exception

enum class BooksApiStatus { LOADING, EMPTY_RESULT, ERROR, DONE }

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

    private val _loadingStatus = MutableLiveData<BooksApiStatus>()
    val loadingStatus: LiveData<BooksApiStatus>
        get() = _loadingStatus


    init {
        _searchParams.value = searchParams
        getBooks(searchParams)
        saveToHistory(searchParams)
    }


    private fun getBooks(searchParams: SearchParams) {
        viewModelScope.launch {
            try {
                _loadingStatus.value = BooksApiStatus.LOADING
                _books.value = booksRepository.getBooksFromApi(searchParams)

                if (books.value == null || books.value?.isEmpty() == true)
                    _loadingStatus.value = BooksApiStatus.EMPTY_RESULT
                else {
                    _loadingStatus.value = BooksApiStatus.DONE
                }
            }
            catch (e: Exception) {
                _books.value = ArrayList()
                _loadingStatus.value = BooksApiStatus.ERROR
                Log.d("SearchResultViewModel", "Error while fetching books: ${e.message}")
            }
        }
    }

    fun saveBook(book: Book) {
        viewModelScope.launch {
            book.savingDate = currentDateAsString()
            booksRepository.insertBook(book)
        }
    }

    private fun saveToHistory(searchParams: SearchParams) {
        viewModelScope.launch {
            searchParams.date = currentDateAsString()
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
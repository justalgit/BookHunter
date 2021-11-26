package com.example.bookhunter.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.bookhunter.network.BooksApi
import com.example.bookhunter.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(private val database: BooksDatabase) {

    val historySearchParams: LiveData<List<SearchParams>> = database.searchParamsDao.getAll()

    fun getBooksOrderedByDate(): LiveData<List<Book>> {
        return database.bookDao.getAllSortedByDate()
    }

    fun getBooksOrderedByTitle(): LiveData<List<Book>> {
        return database.bookDao.getAllSortedByTitle()
    }


    suspend fun getBooksFromApi(searchParams: SearchParams) = BooksApi.retrofitService.getBooks(
            searchParams.searchQuery,
            searchParams.maxResults
    ).asDatabaseModel()


    suspend fun insertSearchParams(searchParams: SearchParams) {
        withContext(Dispatchers.IO) {
            database.searchParamsDao.insert(searchParams)
        }
    }


    suspend fun insertBook(book: Book) {
        withContext(Dispatchers.IO) {
            database.bookDao.insert(book)
        }
    }


    suspend fun clearHistory() {
        withContext(Dispatchers.IO) {
            database.searchParamsDao.deleteAll()
        }
    }


    suspend fun clearSavedBooks() {
        withContext(Dispatchers.IO) {
            database.bookDao.deleteAll()
        }
    }
}
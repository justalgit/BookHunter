package com.example.bookhunter.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.bookhunter.network.BooksApi
import com.example.bookhunter.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(private val database: BooksDatabase) {

    private val history = database.searchParamsDao.getAll()

    /**
     * Book fetching from remote API
     */

    suspend fun getBooksFromApi(searchParams: SearchParams) = BooksApi.retrofitService.getBooks(
        searchParams.searchQuery,
        searchParams.maxResults
    ).asDatabaseModel()

    /**
     * Book DAO methods
     */

    fun getBooksOrderedByTitle(): LiveData<List<Book>> {
        return database.bookDao.getAllSortedByTitle()
    }

    fun getBooksOrderedByDate(): LiveData<List<Book>> {
        return database.bookDao.getAllSortedByDate()
    }

    suspend fun insertBook(book: Book) {
        withContext(Dispatchers.IO) {
            database.bookDao.insert(book)
        }
    }

    suspend fun updateBook(book: Book) {
        withContext(Dispatchers.IO) {
            database.bookDao.update(book)
        }
    }

    suspend fun deleteBook(book: Book) {
        withContext(Dispatchers.IO) {
            database.bookDao.delete(book)
        }
    }

    suspend fun clearSavedBooks() {
        withContext(Dispatchers.IO) {
            database.bookDao.deleteAll()
        }
    }

    /**
     * SearchParams (history items) DAO methods
     */

    fun getHistory(): LiveData<List<SearchParams>> {
        return history
    }

    suspend fun insertSearchParams(searchParams: SearchParams) {
        withContext(Dispatchers.IO) {
            database.searchParamsDao.insert(searchParams)
        }
    }

    suspend fun clearHistory() {
        withContext(Dispatchers.IO) {
            database.searchParamsDao.deleteAll()
        }
    }
}
package com.example.bookhunter.utils

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhunter.R
import com.example.bookhunter.adapters.HistoryAdapter
import com.example.bookhunter.adapters.SavedBooksAdapter
import com.example.bookhunter.adapters.SearchResultAdapter
import com.example.bookhunter.database.Book
import com.example.bookhunter.database.SearchParams
import com.example.bookhunter.viewmodels.BooksApiStatus

/**
 * RecyclerView data bindings
 */

@BindingAdapter("searchedBooksData")
fun bindSearchedBooksRecyclerView(recyclerView: RecyclerView, data: List<Book>?) {
    val adapter = recyclerView.adapter as SearchResultAdapter
    adapter.submitList(data)
}

@BindingAdapter("historyData")
fun bindHistoryRecyclerView(recyclerView: RecyclerView, data: List<SearchParams>?) {
    val adapter = recyclerView.adapter as HistoryAdapter
    adapter.submitList(data)
}

@BindingAdapter("savedBooksData")
fun bindSavedBooksRecyclerView(recyclerView: RecyclerView, data: List<Book>?) {
    val adapter = recyclerView.adapter as SavedBooksAdapter
    adapter.submitList(data)
}

/**
 * No data message visibility binding
 */

@BindingAdapter("messageVisibility")
fun <T: Any> bindNoDataMessageVisibility(textView: TextView, data: List<T>?) {
    when (data?.isEmpty() == true) {
        true -> textView.visibility = View.VISIBLE
        false -> textView.visibility = View.GONE
    }
}

/**
 * Loading status TextView binding
 */

@BindingAdapter("loadingStatus")
fun bindLoadingStatusToTextView(textView: TextView, status: BooksApiStatus) {
    when (status) {
        BooksApiStatus.DONE -> textView.visibility = View.GONE
        BooksApiStatus.LOADING -> textView.setText(R.string.loading_message)
        BooksApiStatus.EMPTY_RESULT -> textView.setText(R.string.empty_result_message)
        BooksApiStatus.ERROR -> textView.setText(R.string.loading_error_message)
    }
}

@BindingAdapter("bookNoteText")
fun bindBookNoteToEditText(editText: EditText, book: Book) {
    when (book.note) {
        null -> editText.hint = "Add your note or description here"
        else -> editText.hint = book.note
    }
}
package com.example.bookhunter.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhunter.adapters.HistoryAdapter
import com.example.bookhunter.adapters.SavedBooksAdapter
import com.example.bookhunter.adapters.SearchResultAdapter
import com.example.bookhunter.database.Book
import com.example.bookhunter.database.SearchParams

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
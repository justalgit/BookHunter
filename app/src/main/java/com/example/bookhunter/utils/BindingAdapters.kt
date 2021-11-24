package com.example.bookhunter.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhunter.adapters.SearchResultAdapter
import com.example.bookhunter.database.entities.Book

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Book>?) {
    val adapter = recyclerView.adapter as SearchResultAdapter
    adapter.submitList(data)
}
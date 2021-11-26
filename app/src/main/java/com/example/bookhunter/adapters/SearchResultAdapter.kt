package com.example.bookhunter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhunter.databinding.ResultBookViewItemBinding
import com.example.bookhunter.database.Book

class SearchResultAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Book, SearchResultAdapter.BookViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(ResultBookViewItemBinding.inflate(
            LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book, onClickListener)
    }


    class BookViewHolder(private var binding: ResultBookViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book, onClickListener: OnClickListener) {
            binding.book = book
            binding.clickListener = onClickListener
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }
    }


    class OnClickListener(val clickListener: (book: Book) -> Unit) {
        fun onClick(book: Book) = clickListener(book)
    }
}
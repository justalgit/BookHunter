package com.example.bookhunter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhunter.databinding.SavedBookViewItemBinding
import com.example.bookhunter.database.entities.Book

class SavedBookAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Book, SavedBookAdapter.BookViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(SavedBookViewItemBinding.inflate(
            LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(book)
        }
        holder.bind(book)
    }


    class BookViewHolder(private var binding: SavedBookViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
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
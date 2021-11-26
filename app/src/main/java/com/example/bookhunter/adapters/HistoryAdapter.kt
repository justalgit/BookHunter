package com.example.bookhunter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhunter.databinding.HistoryViewItemBinding
import com.example.bookhunter.database.SearchParams

class HistoryAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<SearchParams, HistoryAdapter.HistoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryViewItemBinding.inflate(
            LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyRecord = getItem(position)
        holder.bind(historyRecord, onClickListener)
    }


    class HistoryViewHolder(private var binding: HistoryViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(searchParams: SearchParams, onClickListener: OnClickListener) {
            binding.searchParams = searchParams
            binding.clickListener = onClickListener
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<SearchParams>() {
        override fun areItemsTheSame(oldItem: SearchParams, newItem: SearchParams): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: SearchParams, newItem: SearchParams): Boolean {
            return oldItem.id == newItem.id
        }
    }


    class OnClickListener(val clickListener: (searchParams: SearchParams) -> Unit) {
        fun onClick(searchParams: SearchParams) = clickListener(searchParams)
    }
}
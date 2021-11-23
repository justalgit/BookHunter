package com.example.bookhunter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhunter.databinding.HistoryViewItemBinding
import com.example.bookhunter.database.HistoryRecord

class HistoryAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<HistoryRecord, HistoryAdapter.HistoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryViewItemBinding.inflate(
            LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyRecord = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(historyRecord)
        }
        holder.bind(historyRecord)
    }


    class HistoryViewHolder(private var binding: HistoryViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(historyRecord: HistoryRecord) {
            binding.historyRecord = historyRecord
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<HistoryRecord>() {
        override fun areItemsTheSame(oldItem: HistoryRecord, newItem: HistoryRecord): Boolean {
            return oldItem === newItem
        }
        override fun areContentsTheSame(oldItem: HistoryRecord, newItem: HistoryRecord): Boolean {
            return oldItem.id == newItem.id
        }
    }


    class OnClickListener(val clickListener: (historyRecord: HistoryRecord) -> Unit) {
        fun onClick(historyRecord: HistoryRecord) = clickListener(historyRecord)
    }
}
package com.example.bookhunter.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookhunter.R
import com.example.bookhunter.adapters.HistoryAdapter
import com.example.bookhunter.adapters.SearchResultAdapter
import com.example.bookhunter.databinding.FragmentHistoryBinding
import com.example.bookhunter.databinding.FragmentSearchResultBinding
import com.example.bookhunter.viewmodels.*
import com.google.android.material.snackbar.Snackbar


class HistoryFragment : Fragment() {

    private val viewModel: HistoryViewModel by lazy {
        val activity = requireNotNull(this.activity)
        val viewModelFactory = HistoryViewModelFactory(activity.application)
        ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHistoryBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_history, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.historyList.adapter = HistoryAdapter(HistoryAdapter.OnClickListener {
            Toast.makeText(context, it.searchQuery, Toast.LENGTH_SHORT).show()
        })

        binding.historyList.layoutManager = LinearLayoutManager(context)

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear_history -> {
                viewModel.clearHistory()
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.history_cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }

}
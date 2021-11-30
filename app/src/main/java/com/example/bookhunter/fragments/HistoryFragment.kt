package com.example.bookhunter.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookhunter.R
import com.example.bookhunter.adapters.HistoryAdapter
import com.example.bookhunter.databinding.FragmentHistoryBinding
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
            Toast.makeText(
                context, getString(R.string.repeating_search_message), Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(
                HistoryFragmentDirections.actionHistoryFragmentToSearchResultFragment(it)
            )
        })

        binding.historyList.layoutManager = GridLayoutManager(activity, 1)

        setHasOptionsMenu(true)

        return binding.root
    }


    private fun historyDeletingAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle(title)
            setMessage(message)

            setPositiveButton(getString(R.string.alert_yes)) { _, _ ->
                viewModel.clearHistory()
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.books_cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            setNegativeButton(getString(R.string.alert_no), null)

            show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var noHistory = true

        viewModel.history.observe(this, Observer {
            noHistory = it.isNullOrEmpty()
        })

        when (item.itemId) {
            R.id.clear_history -> {
                if (noHistory || noHistory == null) {
                    Toast.makeText(
                        context, getString(R.string.nothing_to_delete), Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    historyDeletingAlert(
                        getString(R.string.clear_history_alert_title),
                        getString(R.string.clear_history_alert_message)
                    )
                }
            }
        }

        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}
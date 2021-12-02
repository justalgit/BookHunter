package com.example.bookhunter.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookhunter.R
import com.example.bookhunter.adapters.SearchResultAdapter
import com.example.bookhunter.databinding.FragmentSearchResultBinding
import com.example.bookhunter.viewmodels.SearchResultViewModel
import com.example.bookhunter.viewmodels.SearchResultViewModelFactory


class SearchResultFragment : Fragment() {

        private val viewModel: SearchResultViewModel by lazy {
            val activity = requireNotNull(this.activity)
            val arguments = SearchResultFragmentArgs.fromBundle(requireArguments())
            val viewModelFactory = SearchResultViewModelFactory(
                arguments.searchParams,
                activity.application
            )
            ViewModelProvider(this, viewModelFactory).get(SearchResultViewModel::class.java)
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            val binding: FragmentSearchResultBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search_result, container, false)

            binding.lifecycleOwner = this
            binding.viewModel = viewModel

            var isAnyBookSaved = false

            binding.booksList.adapter = SearchResultAdapter(SearchResultAdapter.OnClickListener {
                viewModel.saveBook(it)
                isAnyBookSaved = true
                Toast.makeText(
                    context, getString(R.string.book_saved, it.title), Toast.LENGTH_SHORT
                ).show()
            })

            binding.booksList.layoutManager = GridLayoutManager(activity, 1)

            viewModel.isNavigatingToOverview.observe(viewLifecycleOwner, Observer {
                if (it) {
                    if (!isAnyBookSaved) {
                        noBooksSavedAlert(
                            this,
                            getString(R.string.nothing_saved_alert_title),
                            getString(R.string.nothing_saved_alert_message)
                        )
                    }
                    else {
                        navigateToOverview(this)
                    }
                }
            })

            return binding.root
        }


    private fun noBooksSavedAlert(fragment: SearchResultFragment, title: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle(title)
            setMessage(message)

            setPositiveButton(getString(R.string.alert_yes)) { _, _ ->
                navigateToOverview(fragment)
            }

            setNegativeButton(getString(R.string.alert_no), null)

            show()
        }
    }


    private fun navigateToOverview(fragment: SearchResultFragment) {
        fragment.findNavController().navigate(
            SearchResultFragmentDirections.actionSearchResultFragmentToOverviewFragment()
        )
        viewModel.navigateToOverviewDone()
    }
}
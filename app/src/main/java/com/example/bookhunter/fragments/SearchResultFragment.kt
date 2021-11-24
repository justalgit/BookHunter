package com.example.bookhunter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookhunter.R
import com.example.bookhunter.adapters.SearchResultAdapter
import com.example.bookhunter.databinding.FragmentSearchResultBinding
import com.example.bookhunter.viewmodels.SearchResultViewModel
import com.example.bookhunter.viewmodels.SearchResultViewModelFactory


class SearchResultFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            val arguments = SearchResultFragmentArgs.fromBundle(requireArguments())
            val viewModelFactory = SearchResultViewModelFactory(arguments.searchParams)
            val viewModel = ViewModelProvider(this, viewModelFactory)
                .get(SearchResultViewModel::class.java)

            val binding: FragmentSearchResultBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_search_result, container, false)

            binding.lifecycleOwner = this
            binding.viewModel = viewModel

            binding.booksList.adapter = SearchResultAdapter(SearchResultAdapter.OnClickListener {
                Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
            })

            binding.booksList.layoutManager = LinearLayoutManager(context)

            viewModel.isNavigatingToOverview.observe(viewLifecycleOwner, Observer {
                if (it) {
                    this.findNavController().navigate(
                        SearchResultFragmentDirections.actionSearchResultFragmentToOverviewFragment()
                    )
                    viewModel.navigateToOverviewDone()
                }
            })

            return binding.root
        }

}
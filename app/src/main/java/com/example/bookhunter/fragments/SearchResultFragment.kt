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

            binding.booksList.adapter = SearchResultAdapter(SearchResultAdapter.OnClickListener {
                viewModel.saveBook(it)
                Toast.makeText(
                    context, getString(R.string.book_saved, it.title), Toast.LENGTH_SHORT
                ).show()
            })

            binding.booksList.layoutManager = GridLayoutManager(activity, 1)

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
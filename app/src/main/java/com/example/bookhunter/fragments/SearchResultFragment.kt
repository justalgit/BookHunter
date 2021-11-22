package com.example.bookhunter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookhunter.R
import com.example.bookhunter.databinding.FragmentSearchBinding
import com.example.bookhunter.databinding.FragmentSearchResultBinding
import com.example.bookhunter.viewmodels.SearchResultViewModel
import com.example.bookhunter.viewmodels.SearchViewModel


class SearchResultFragment : Fragment() {

    private val viewModel: SearchResultViewModel by lazy {
        ViewModelProvider(this).get(SearchResultViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSearchResultBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search_result, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.isNavigatingToOverview.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(
                    SearchResultFragmentDirections.actionSearchResultFragmentToOverviewFragment()
                )
                viewModel.navigateToOverviewDone()
            }
        })

        return binding.root
    }

}
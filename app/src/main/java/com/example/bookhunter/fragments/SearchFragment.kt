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
import com.example.bookhunter.databinding.FragmentOverviewBinding
import com.example.bookhunter.databinding.FragmentSearchBinding
import com.example.bookhunter.viewmodels.OverviewViewModel
import com.example.bookhunter.viewmodels.SearchViewModel


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.isNavigatingToResult.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToSearchResultFragment()
                )
                viewModel.navigateToResultDone()
            }
        })

        return binding.root
    }

}
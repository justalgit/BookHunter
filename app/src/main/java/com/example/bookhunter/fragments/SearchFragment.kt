package com.example.bookhunter.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookhunter.R
import com.example.bookhunter.databinding.FragmentSearchBinding
import com.example.bookhunter.viewmodels.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import com.example.bookhunter.database.SearchParams
import androidx.core.content.ContextCompat.getSystemService
import com.example.bookhunter.utils.hideKeyboard


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

        viewModel.isInputValid.observe(viewLifecycleOwner, Observer {
            if (!it) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.invalid_input_message),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.isNavigatingToResult.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.hideKeyboard()
                this.findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(it)
                )
                viewModel.navigateToResultDone()
            }
        })

        return binding.root
    }


    override fun onDestroyView() {
        this.hideKeyboard()
        super.onDestroyView()
    }
}
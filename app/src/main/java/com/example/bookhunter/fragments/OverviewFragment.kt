package com.example.bookhunter.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookhunter.R
import com.example.bookhunter.databinding.FragmentOverviewBinding
import com.example.bookhunter.viewmodels.OverviewViewModel
import com.google.android.material.snackbar.Snackbar


class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_overview, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.isNavigatingToSearch.observe(viewLifecycleOwner, Observer {
            if (it) {
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionOverviewFragmentToSearchFragment()
                )
                viewModel.navigateToSearchDone()
            }
        })

        viewModel.isNavigatingToHistory.observe(viewLifecycleOwner, Observer {
            if (it) {
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionOverviewFragmentToHistoryFragment()
                )
                viewModel.navigateToHistoryDone()
            }
        })

        viewModel.isNavigatingToAbout.observe(viewLifecycleOwner, Observer {
            if (it) {
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionOverviewFragmentToAboutFragment()
                )
                viewModel.navigateToAboutDone()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overview_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_history -> viewModel.navigateToHistory()
            R.id.sort_by_date -> Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                getString(R.string.sorted_by_date_message),
                Snackbar.LENGTH_SHORT
            ).show()
            R.id.sort_by_book_name -> Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                getString(R.string.sorted_by_book_name_message),
                Snackbar.LENGTH_SHORT
            ).show()
            R.id.show_about -> viewModel.navigateToAbout()
        }
        return true
    }

}

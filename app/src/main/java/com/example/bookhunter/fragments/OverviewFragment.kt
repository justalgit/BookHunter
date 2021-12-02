package com.example.bookhunter.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookhunter.R
import com.example.bookhunter.adapters.SavedBooksAdapter
import com.example.bookhunter.databinding.FragmentOverviewBinding
import com.example.bookhunter.viewmodels.OverviewViewModel
import com.example.bookhunter.viewmodels.OverviewViewModelFactory


class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        val activity = requireNotNull(this.activity)
        val viewModelFactory = OverviewViewModelFactory(activity.application)
        ViewModelProvider(this, viewModelFactory).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_overview, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.savedBooksList.adapter = SavedBooksAdapter(SavedBooksAdapter.OnClickListener {
            this.findNavController().navigate(
                OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it)
            )
        })

        binding.savedBooksList.layoutManager = GridLayoutManager(activity, 1)

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

        var noSavedBooks = true

        viewModel.savedBooks!!.observe(this, Observer {
            noSavedBooks = it.isNullOrEmpty()
        })

        when (item.itemId) {

            R.id.sort_by_date -> {
                when {
                    noSavedBooks || noSavedBooks == null -> {
                        showShortToast(getString(R.string.nothing_to_sort))
                    }
                    viewModel.sortBooksByDate() -> {
                        showShortToast(getString(R.string.sorted_by_date_message))
                    }
                    else -> {
                        showShortToast(getString(R.string.already_sorted_message))
                    }
                }
            }

            R.id.sort_by_book_name -> {
                when {
                    noSavedBooks || noSavedBooks == null -> {
                        showShortToast(getString(R.string.nothing_to_sort))
                    }
                    viewModel.sortBooksByName() -> {
                        showShortToast(getString(R.string.sorted_by_book_name_message))
                    }
                    else -> {
                        showShortToast(getString(R.string.already_sorted_message))
                    }
                }
            }

            R.id.clear_saved_books -> {
                if (noSavedBooks || noSavedBooks == null) {
                    showShortToast(getString(R.string.nothing_to_delete))
                }
                else {
                    booksDeletingAlert(
                        getString(R.string.clear_books_alert_title),
                        getString(R.string.clear_books_alert_message)
                    )
                }
            }

        }

        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


    private fun booksDeletingAlert(title: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle(title)
            setMessage(message)

            setPositiveButton(getString(R.string.alert_yes)) { _, _ ->
                viewModel.clearSavedBooks()
                showShortToast(getString(R.string.books_cleared_message))
            }

            setNegativeButton(getString(R.string.alert_no), null)

            show()
        }
    }

    private fun showShortToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

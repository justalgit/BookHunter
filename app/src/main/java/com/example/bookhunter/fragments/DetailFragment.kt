package com.example.bookhunter.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.bookhunter.R
import com.example.bookhunter.databinding.FragmentDetailBinding
import com.example.bookhunter.databinding.FragmentOverviewBinding
import com.example.bookhunter.viewmodels.DetailViewModel
import com.example.bookhunter.viewmodels.DetailViewModelFactory
import com.example.bookhunter.viewmodels.OverviewViewModel
import com.example.bookhunter.viewmodels.OverviewViewModelFactory
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by lazy {
        val activity = requireNotNull(this.activity)
        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = DetailViewModelFactory(
            args.selectedBook,
            activity.application
        )
        ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.isNavigatingToOverview.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragmentToOverviewFragment()
                )
                viewModel.navigateToOverviewDone()
            }
        })

        viewModel.isBookUpdated.observe(viewLifecycleOwner, Observer {
            if (it) {
                showShortSnackbar(getString(R.string.book_updated_message))
            }
        })

        viewModel.isBookDeleted.observe(viewLifecycleOwner, Observer {
            if (it) {
                showShortSnackbar(getString(R.string.book_deleted_message))
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu, menu)
        if (getShareIntent().resolveActivity(requireActivity().packageManager) == null) {
            menu.findItem(R.id.share_book).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share_book -> shareBook()
        }
        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


    private fun shareBook() {
        startActivity(getShareIntent())
    }

    private fun getShareIntent() : Intent {
        val book = viewModel.selectedBook.value

        val shareIntent = Intent(Intent.ACTION_SEND)

        // TODO: fix extras for explicit intent

        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(
                R.string.share_book_message,
                book?.title,
                book?.url
            ))

        Log.d("Detail fragment", shareIntent.extras.toString())
        return shareIntent
    }


    private fun showShortSnackbar(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
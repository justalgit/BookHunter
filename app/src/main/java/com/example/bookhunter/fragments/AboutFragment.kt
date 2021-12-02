package com.example.bookhunter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.bookhunter.R
import com.example.bookhunter.databinding.FragmentAboutBinding
import com.example.bookhunter.databinding.FragmentDetailBinding


class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAboutBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_about, container, false)

        binding.bookHunterDescription.text = getText(R.string.about_message)

        return binding.root
    }

}
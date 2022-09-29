package com.example.movieapp.detail.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapp.detail.databinding.FragmentDetailBinding
import com.example.movieapp.presentation.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun contentSetup(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}
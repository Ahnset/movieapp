package com.example.movieapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<T> : Fragment() {

    protected var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return contentSetup(inflater, container)
    }

    abstract fun contentSetup(inflater: LayoutInflater, container: ViewGroup?): View?

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

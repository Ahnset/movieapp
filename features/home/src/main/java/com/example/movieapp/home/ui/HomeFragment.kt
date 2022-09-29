package com.example.movieapp.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.home.databinding.FragmentHomeBinding
import com.example.movieapp.home.redux.HomeState
import com.example.movieapp.home.redux.HomeState.GetHomeCatalogsStarted
import com.example.movieapp.home.redux.HomeState.HomeCatalogsError
import com.example.movieapp.home.redux.HomeState.HomeCatalogsLoaded
import com.example.movieapp.home.redux.HomeState.Idle
import com.example.movieapp.home.redux.HomeViewModel
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.model.MovieUI
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun contentSetup(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start()
        observeViewState()
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenResumed {
            viewModel.viewState?.collect { viewState ->
                Log.d("ViewState", viewState.toString())
                renderView(viewState)
            }
        }
    }

    private fun renderView(viewState: HomeState) {
        when (viewState) {
            is Idle -> { /* Do nothing */ }
            is GetHomeCatalogsStarted -> showLoading()
            is HomeCatalogsLoaded -> showMovieCatalogs(viewState)
            is HomeCatalogsError -> showErrorMessage(viewState.message)
        }
    }

    private fun showLoading() {
        binding.homeContent.isVisible = false
        binding.errorContent.isVisible = false
        binding.homeProgress.isVisible = true
    }

    private fun hideLoading() {
        binding.homeContent.isVisible = true
        binding.homeProgress.isVisible = false
        binding.errorContent.isVisible = false
    }

    private fun displayErrorContent() {
        binding.homeProgress.isVisible = false
        binding.homeContent.isVisible = false
        binding.errorContent.isVisible = true
    }

    private fun showErrorMessage(message: String?) {
        binding.apply {
            errorMessage.text = message
            errorRetryButton.setOnClickListener {
                viewModel.start()
            }
        }
        displayErrorContent()
    }

    private fun showMovieCatalogs(viewState: HomeCatalogsLoaded) {
        val (trendingMovies, topRatedMovies, popularMovies, upcomingMovies) = viewState
        setupTrendingSlider(trendingMovies)
        setupMovieList(binding.popularList, popularMovies)
        setupMovieList(binding.topRatedList, topRatedMovies)
        setupMovieList(binding.upcomingList, upcomingMovies)
        hideLoading()
    }

    private fun setupTrendingSlider(trendingMovies: List<MovieUI>) {
        val sliderAdapter = SliderAdapter(trendingMovies, ::onMovieClicked)
        binding.apply {
            slider.adapter = sliderAdapter
            indicator.setupWithViewPager(slider, true)
        }
    }

    private fun setupMovieList(list: RecyclerView, movies: List<MovieUI>) {
        val moviesAdapter = MoviesAdapter(::onMovieClicked)
        list.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        moviesAdapter.submitList(movies)
    }

    private fun onMovieClicked(id: Int) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "TODO:// Add Detail Feature for Movie $id", Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        binding.apply {
            popularList.adapter = null
            upcomingList.adapter = null
            topRatedList.adapter = null
            slider.adapter = null
        }

        super.onDestroyView()
    }
}

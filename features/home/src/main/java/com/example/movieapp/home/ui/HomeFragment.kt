package com.example.movieapp.home.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.example.movieapp.home.databinding.FragmentHomeBinding
import com.example.movieapp.home.redux.HomeState
import com.example.movieapp.home.redux.HomeState.GetHomeCatalogsStarted
import com.example.movieapp.home.redux.HomeState.HomeCatalogsError
import com.example.movieapp.home.redux.HomeState.HomeCatalogsLoaded
import com.example.movieapp.home.redux.HomeState.Idle
import com.example.movieapp.home.redux.HomeViewModel
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.common.NavigationHelper.DETAIL_DEEP_LINK
import com.example.movieapp.presentation.common.UiHelper.hideViews
import com.example.movieapp.presentation.common.UiHelper.showViews
import com.example.movieapp.presentation.model.MovieUI
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
                renderView(viewState)
            }
        }
    }

    private fun renderView(viewState: HomeState) {
        when (viewState) {
            is Idle -> return
            is GetHomeCatalogsStarted -> showLoading()
            is HomeCatalogsLoaded -> showMovieCatalogs(viewState)
            is HomeCatalogsError -> showErrorMessage(viewState.message)
        }
    }

    private fun showLoading() {
        binding.apply {
            hideViews(homeContent, errorContent)
            showViews(homeProgress)
        }
    }

    private fun hideLoading() {
        binding.apply {
            showViews(homeContent)
            hideViews(homeProgress, errorContent)
        }
    }

    private fun displayErrorContent() {
        binding.apply {
            hideViews(homeProgress, homeContent)
            showViews(errorContent)
        }
    }

    private fun showErrorMessage(message: String?) {
        binding.apply {
            errorMessage.text = message
            errorRetryButton.setOnClickListener {
                viewModel.retry()
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
            moviesAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
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
        val destination = Uri.parse("$DETAIL_DEEP_LINK$id")
        findNavController().navigate(destination)
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

package com.example.movieapp.detail.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.example.movieapp.detail.databinding.FragmentDetailBinding
import com.example.movieapp.detail.redux.DetailState
import com.example.movieapp.detail.redux.DetailState.GetMovieInfoStarted
import com.example.movieapp.detail.redux.DetailState.Idle
import com.example.movieapp.detail.redux.DetailState.MovieInfoError
import com.example.movieapp.detail.redux.DetailState.MovieInfoLoaded
import com.example.movieapp.detail.redux.DetailViewModel
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.common.GlideHelper.setAsyncImage
import com.example.movieapp.presentation.common.ListHelper.stringify
import com.example.movieapp.presentation.common.NavigationHelper
import com.example.movieapp.presentation.common.PlayerHelper.VIDEO_KEY
import com.example.movieapp.presentation.common.UiHelper.hideViews
import com.example.movieapp.presentation.common.UiHelper.showSnackMessage
import com.example.movieapp.presentation.common.UiHelper.showViews
import com.example.movieapp.presentation.model.CastUI
import com.example.movieapp.presentation.model.DetailUI
import com.example.movieapp.presentation.model.MovieUI
import com.example.movieapp.presentation.model.VideoUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun contentSetup(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start(args.movieId)
        observeViewState()
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenResumed {
            viewModel.viewState?.collect { viewState ->
                Log.i("ViewState", viewState.toString())
                renderView(viewState)
            }
        }
    }

    private fun renderView(viewState: DetailState) {
        when (viewState) {
            is Idle -> return
            is GetMovieInfoStarted -> showLoading()
            is MovieInfoLoaded -> showDetail(viewState)
            is MovieInfoError -> showError(viewState.message)
        }
    }

    private fun showLoading() {
        binding.apply {
            hideViews(detailContent, /* errorContent */)
            showViews(detailProgress)
        }
    }

    private fun showError(message: String?) {
        // TODO: Implement ErrorView
    }

    private fun showDetail(viewState: MovieInfoLoaded) {
        viewState.apply {
            setMoviePoster(detail.posterPath)
            setPlayButton(videos)
            setMovieMetaData(detail)
            setMovieRating(detail)
            setMovieCast(binding.movieCast, cast)
            setSimilarMovies(binding.movieSimilar, similarMovies)
            onSaveToBookmarkButtonClicked(detail.id)
            onBackButtonClicked()
        }
        hideLoading()
    }

    private fun setPlayButton(videos: List<VideoUI>) {
        if (videos.isEmpty()) return

        binding.apply {
            detailPlayButton.setOnClickListener {
                startPlayerActivity(videos)
            }
            showViews(detailPlayButton)
        }
    }

    private fun startPlayerActivity(videos: List<VideoUI>) {
        val key = videos.first().key
        val intent = Intent(requireActivity(), PlayerActivity::class.java)
        intent.putExtra(VIDEO_KEY, key)
        startActivity(intent)
    }

    private fun onBackButtonClicked() {
        binding.detailBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun onSaveToBookmarkButtonClicked(id: Int) {
        binding.apply {
            detailBookmarkButton.setOnClickListener {
                val message = "TODO:// Add Bookmark feature."
                showSnackMessage(message, detailContent)
            }
        }
    }

    private fun setMovieRating(detail: DetailUI) {
        binding.apply {
            rating.rating = detail.voteAverage.toFloat() / 2
            voteCount.text = "(${detail.voteCount})"
        }
    }

    private fun setMovieMetaData(detail: DetailUI) {
        binding.apply {
            movieTitle.text = detail.title
            movieGenre.text = detail.genres.stringify()
            movieReleaseDate.text = detail.releaseDate
            movieOverviewText.text = detail.overview
        }
    }

    private fun setMoviePoster(posterPath: String?) {
        binding.apply {
            moviePoster.setAsyncImage(requireContext(), posterPath)
            movieThumbnail.setAsyncImage(requireContext(), posterPath)
        }
    }

    private fun setMovieCast(list: RecyclerView, cast: List<CastUI>) {
        val castAdapter = CastAdapter()

        list.apply {
            castAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
            adapter = castAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        castAdapter.submitList(cast)
    }

    private fun setSimilarMovies(list: RecyclerView, movies: List<MovieUI>) {
        val movieAdapter = SimilarMovieAdapter(::onMovieClicked)

        list.apply {
            movieAdapter.stateRestorationPolicy = PREVENT_WHEN_EMPTY
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        movieAdapter.submitList(movies)
    }

    private fun onMovieClicked(id: Int) {
        val destination = Uri.parse("${NavigationHelper.DETAIL_DEEP_LINK}$id")
        findNavController().navigate(destination)
    }

    private fun hideLoading() {
        binding.apply {
            showViews(detailContent)
            hideViews(detailProgress, /* errorContent */)
        }
    }

    override fun onDestroyView() {
        binding.apply {
            movieCast.adapter = null
            movieSimilar.adapter = null
        }
        super.onDestroyView()
    }
}

package com.example.movieapp.home.redux

import com.example.movieapp.core.redux.State
import com.example.movieapp.presentation.model.MovieUI

sealed class HomeState : State {
    object Idle : HomeState()
    object GetHomeCatalogsStarted : HomeState()
    data class HomeCatalogsError(val message: String?) : HomeState()

    data class HomeCatalogsLoaded(
        val trendingMovies: List<MovieUI>,
        val topRatedMovies: List<MovieUI>,
        val popularMovies: List<MovieUI>,
        val upcomingMovies: List<MovieUI>
    ) : HomeState()
}

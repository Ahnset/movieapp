package com.example.movieapp.home.redux

import com.example.movieapp.core.redux.Action
import com.example.movieapp.presentation.model.MovieUI

sealed class HomeAction : Action {
    object Idle : HomeAction()
    object GetHomeCatalogs : HomeAction()
    data class GetHomeCatalogsError(val message: String?) : HomeAction()

    data class GetHomeCatalogsLoaded(
        val popularMovies: List<MovieUI>,
        val trendingMovies: List<MovieUI>,
        val topRatedMovies: List<MovieUI>,
        val upcomingMovies: List<MovieUI>
    ) : HomeAction()
}

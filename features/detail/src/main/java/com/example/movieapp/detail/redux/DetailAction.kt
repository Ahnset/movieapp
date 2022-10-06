package com.example.movieapp.detail.redux

import com.example.movieapp.core.redux.Action
import com.example.movieapp.presentation.model.CastUI
import com.example.movieapp.presentation.model.DetailUI
import com.example.movieapp.presentation.model.MovieUI
import com.example.movieapp.presentation.model.VideoUI

sealed class DetailAction : Action {
    object Idle : DetailAction()
    data class GetMovieInfo(val movieId: Int) : DetailAction()
    data class GetMovieInfoError(val message: String?) : DetailAction()

    data class GetMovieInfoLoaded(
        val detail: DetailUI,
        val cast: List<CastUI>,
        val videos: List<VideoUI>,
        val similarMovies: List<MovieUI>
    ) : DetailAction()
}

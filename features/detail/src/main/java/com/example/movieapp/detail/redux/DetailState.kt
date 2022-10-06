package com.example.movieapp.detail.redux

import com.example.movieapp.core.redux.State
import com.example.movieapp.presentation.model.CastUI
import com.example.movieapp.presentation.model.DetailUI
import com.example.movieapp.presentation.model.MovieUI
import com.example.movieapp.presentation.model.VideoUI

sealed class DetailState : State {
    object Idle : DetailState()
    object GetMovieInfoStarted : DetailState()
    data class MovieInfoError(val message: String?) : DetailState()

    data class MovieInfoLoaded(
        val detail: DetailUI,
        val cast: List<CastUI>,
        val videos: List<VideoUI>,
        val similarMovies: List<MovieUI>
    ) : DetailState()
}

package com.example.movieapp.detail.redux

import com.example.movieapp.core.redux.Reducer
import com.example.movieapp.core.redux.StateMachine
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfo
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfoError
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfoLoaded
import com.example.movieapp.detail.redux.DetailAction.Idle
import com.example.movieapp.detail.redux.DetailState.GetMovieInfoStarted
import com.example.movieapp.detail.redux.DetailState.MovieInfoError
import com.example.movieapp.detail.redux.DetailState.MovieInfoLoaded
import javax.inject.Inject

class DetailStateMachine @Inject constructor(
    detailMiddleware: DetailMiddleware
) : StateMachine<DetailState, DetailAction>(detailMiddleware) {

    override val initialState = DetailState.Idle

    override val reducer: Reducer<DetailState, DetailAction> = { currentState, action ->
        when (action) {
            Idle -> currentState
            is GetMovieInfo -> GetMovieInfoStarted
            is GetMovieInfoError -> MovieInfoError(action.message)
            is GetMovieInfoLoaded -> MovieInfoLoaded(
                action.detail,
                action.cast,
                action.videos,
                action.similarMovies
            )
        }
    }
}

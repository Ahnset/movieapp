package com.example.movieapp.detail.redux

import com.example.movieapp.core.common.Dispatcher
import com.example.movieapp.core.redux.Middleware
import com.example.movieapp.core.redux.Store
import com.example.movieapp.core.usecase.detail.GetMovieInfoUseCase
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfo
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfoError
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfoLoaded
import com.example.movieapp.presentation.common.ListHelper.sliceOrGet
import com.example.movieapp.presentation.mapper.toPresentationModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailMiddleware @Inject constructor(
    private val getMovieInfoUseCase: GetMovieInfoUseCase,
    private val dispatcher: Dispatcher
) : Middleware<DetailState, DetailAction> {

    override suspend fun dispatch(
        currentState: DetailState,
        action: DetailAction,
        store: Store<DetailState, DetailAction>
    ) {

        when (action) {
            is GetMovieInfo -> getMovieInfoSideEffect(action.movieId, store)
            else -> return
        }
    }

    private suspend fun getMovieInfoSideEffect(
        movieId: Int,
        store: Store<DetailState, DetailAction>
    ) {
        getMovieInfoUseCase(movieId).fold(
            { output -> onGetMovieInfoSuccess(output, store) },
            { exception -> onGetMovieInfoError(exception, store) }
        )
    }

    private suspend fun onGetMovieInfoSuccess(
        output: GetMovieInfoUseCase.Output,
        store: Store<DetailState, DetailAction>
    ) {
        withContext(dispatcher.default) {
            output.apply {
                val detail = detail.toPresentationModel()
                val videos = videos.map { it.toPresentationModel() }

                val cast = cast.map { it.toPresentationModel() }
                    .sliceOrGet(9)

                val similarMovies = similarMovies
                    .map { it.toPresentationModel() }
                    .sliceOrGet(4)

                store.dispatch(
                    GetMovieInfoLoaded(
                        detail = detail,
                        cast = cast,
                        videos = videos,
                        similarMovies = similarMovies
                    )
                )
            }
        }
    }

    private suspend fun onGetMovieInfoError(
        exception: Throwable,
        store: Store<DetailState, DetailAction>
    ) {
        store.dispatch(
            GetMovieInfoError(exception.message)
        )
    }
}

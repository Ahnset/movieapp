package com.example.movieapp.core.usecase.home

import com.example.movieapp.core.common.Dispatcher
import com.example.movieapp.core.repository.HomeRepository
import com.example.movieapp.core.usecase.base.BaseSuspendableUseCase
import com.icgen.movieapp.core.model.Movie
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetHomeCatalogsUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val dispatcher: Dispatcher
) : BaseSuspendableUseCase<Unit, GetHomeCatalogsUseCase.Output>() {

    override suspend fun execute(input: Unit?): Output = coroutineScope {
        val popularMovies = async() {
            repository.getPopularMovies()
        }
        val trendingMovies = async(dispatcher.io) {
            repository.getTrendingMovies()
        }
        val topRatedMovies = async(dispatcher.io) {
            repository.getTopRatedMovies()
        }
        val upcomingMovies = async(dispatcher.io) {
            repository.getUpcomingMovies()
        }

        Output(
            popularMovies.await(),
            trendingMovies.await(),
            topRatedMovies.await(),
            upcomingMovies.await()
        )
    }

    data class Output(
        val popularMovies: List<Movie>,
        val trendingMovies: List<Movie>,
        val topRatedMovies: List<Movie>,
        val upcomingMovies: List<Movie>
    )
}

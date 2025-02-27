package com.icgen.movieapp.home.domain.usecase


import android.util.Log
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.usecase.BaseSuspendableUseCase
import com.icgen.movieapp.common.util.Dispatcher
import com.icgen.movieapp.common.util.ListHelper.sliceOrGet
import com.icgen.movieapp.home.domain.repository.CatalogRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
    private val repository: CatalogRepository,
    private val dispatcher: Dispatcher
) : BaseSuspendableUseCase<Unit, GetCatalogUseCase.Output>() {

    override suspend fun execute(input: Unit?): Output = coroutineScope {
        val popularMovies = async(dispatcher.io) {
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

        Log.e(
            "RESPONSE",
            Output(
                popularMovies.await().sliceOrGet(3),
                trendingMovies.await().sliceOrGet(3),
                topRatedMovies.await().sliceOrGet(3),
                upcomingMovies.await().sliceOrGet(3)
            ).toString()
        )

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

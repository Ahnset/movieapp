package com.example.movieapp.core.usecase.home

import com.example.movieapp.core.common.Dispatcher
import com.example.movieapp.core.repository.HomeRepository
import com.example.movieapp.core.usecase.base.BaseSuspendableUseCase
import com.icgen.movieapp.core.model.Movie
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val dispatcher: Dispatcher
) : BaseSuspendableUseCase<Unit, List<Movie>>() {

    override suspend fun execute(input: Unit?): List<Movie> = withContext(dispatcher.io) {
        repository.getPopularMovies()
    }
}

package com.example.movieapp.core.usecase.home

import com.example.movieapp.core.common.DIHelper
import com.example.movieapp.core.repository.HomeRepository
import com.example.movieapp.core.usecase.base.BaseSuspendableUseCase
import com.icgen.movieapp.core.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: HomeRepository,
    @Named(DIHelper.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseSuspendableUseCase<Unit, List<Movie>>() {

    override suspend fun execute(input: Unit?): List<Movie> = withContext(ioDispatcher) {
        repository.getUpcomingMovies()
    }
}

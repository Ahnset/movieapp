package com.icgen.movieapp.home.domain.usecase

import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.usecase.BaseSuspendableUseCase
import com.icgen.movieapp.home.domain.repository.DetailRepository
import javax.inject.Inject

class SaveMovieUseCase @Inject constructor(
    private val repository: DetailRepository
) : BaseSuspendableUseCase<Movie, Unit>() {

    override suspend fun execute(input: Movie?) {
        requireNotNull(input)
        repository.saveMovie(input)
    }
}
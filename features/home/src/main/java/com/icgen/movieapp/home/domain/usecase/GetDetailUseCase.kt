package com.icgen.movieapp.home.domain.usecase

import com.icgen.movieapp.common.domain.model.Cast
import com.icgen.movieapp.common.domain.model.Detail
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.model.Video
import com.icgen.movieapp.common.domain.usecase.BaseSuspendableUseCase
import com.icgen.movieapp.common.util.Dispatcher
import com.icgen.movieapp.home.domain.repository.DetailRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val repository: DetailRepository,
    private val dispatcher: Dispatcher
) : BaseSuspendableUseCase<Int, GetDetailUseCase.Output>() {

    override suspend fun execute(input: Int?): Output = coroutineScope {
        requireNotNull(input)

        val detail = async() {
            repository.getMovieDetail(input)
        }
        val cast = async(dispatcher.io) {
            repository.getMovieCast(input)
        }
        val videos = async(dispatcher.io) {
            repository.getMovieVideos(input)
        }
        val similarMovies = async(dispatcher.io) {
            repository.getSimilarMovies(input)
        }

        Output(
            detail.await(),
            cast.await(),
            videos.await(),
            similarMovies.await()
        )
    }

    data class Output(
        val detail: Detail,
        val cast: List<Cast>,
        val videos: List<Video>,
        val similarMovies: List<Movie>
    )
}

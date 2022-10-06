package com.example.movieapp.core.usecase.detail

import com.example.movieapp.core.common.Dispatcher
import com.example.movieapp.core.model.Cast
import com.example.movieapp.core.model.Detail
import com.example.movieapp.core.model.Video
import com.example.movieapp.core.repository.DetailRepository
import com.example.movieapp.core.usecase.base.BaseSuspendableUseCase
import com.icgen.movieapp.core.model.Movie
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetMovieInfoUseCase @Inject constructor(
    private val repository: DetailRepository,
    private val dispatcher: Dispatcher
) : BaseSuspendableUseCase<Int, GetMovieInfoUseCase.Output>() {

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

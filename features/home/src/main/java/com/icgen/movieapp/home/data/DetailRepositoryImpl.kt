package com.icgen.movieapp.home.data

import com.icgen.movieapp.common.domain.model.Cast
import com.icgen.movieapp.common.domain.model.Detail
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.model.Video
import com.icgen.movieapp.home.data.local.DetailLocalRepository
import com.icgen.movieapp.home.data.remote.detail.DetailRemoteRepository
import com.icgen.movieapp.home.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val remoteRepository: DetailRemoteRepository,
    private val localRepository: DetailLocalRepository
) : DetailRepository {
    override suspend fun getMovieDetail(id: Int): Detail {
        return remoteRepository.getMovieDetail(id)
    }

    override suspend fun getMovieCast(id: Int): List<Cast> {
        return remoteRepository.getMovieCast(id)
    }

    override suspend fun getMovieVideos(id: Int): List<Video> {
        return remoteRepository.getMovieVideos(id)
    }

    override suspend fun getSimilarMovies(id: Int): List<Movie> {
        return remoteRepository.getSimilarMovies(id)
    }

    override suspend fun saveMovie(movie: Movie) {
        return localRepository.saveMovie(movie)
    }
}
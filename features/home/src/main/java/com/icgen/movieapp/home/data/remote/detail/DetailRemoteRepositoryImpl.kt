package com.icgen.movieapp.home.data.remote.detail

import com.icgen.movieapp.common.domain.model.Cast
import com.icgen.movieapp.common.domain.model.Detail
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.model.Video
import com.icgen.movieapp.home.data.mapper.toModel
import com.icgen.movieapp.home.data.remote.service.HomeApiService
import javax.inject.Inject

class DetailRemoteRepositoryImpl @Inject constructor(
    private val apiService: HomeApiService
) : DetailRemoteRepository {
    override suspend fun getMovieDetail(id: Int): Detail {
        return apiService.getMovieDetail(id).toModel()
    }

    override suspend fun getMovieCast(id: Int): List<Cast> {
        return apiService.getMovieCredits(id)
            .cast.map { it.toModel() }
    }

    override suspend fun getMovieVideos(id: Int): List<Video> {
        return apiService.getMoviePreviews(id)
            .results.map { it.toModel() }
    }

    override suspend fun getSimilarMovies(id: Int): List<Movie> {
        return apiService.getSimilarMovies(id)
            .results.map { it.toModel() }
    }
}
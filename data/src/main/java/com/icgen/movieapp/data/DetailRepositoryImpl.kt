package com.icgen.movieapp.data

import com.example.movieapp.core.model.Cast
import com.example.movieapp.core.model.Detail
import com.example.movieapp.core.model.Video
import com.example.movieapp.core.repository.DetailRepository
import com.icgen.movieapp.core.model.Movie
import com.icgen.movieapp.data.mapper.toCoreModel
import com.icgen.movieapp.data.source.detail.DetailApiDataSource
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val apiDataSource: DetailApiDataSource
) : DetailRepository {

    override suspend fun getMovieDetail(id: Int): Detail {
        return apiDataSource.getMovieDetail(id)
            .toCoreModel()
    }

    override suspend fun getMovieCast(id: Int): List<Cast> {
        return apiDataSource.getMovieCast(id)
            .map { it.toCoreModel() }
    }

    override suspend fun getMovieVideos(id: Int): List<Video> {
        return apiDataSource.getMovieVideo(id)
            .map { it.toCoreModel() }
    }

    override suspend fun getSimilarMovies(id: Int): List<Movie> {
        return apiDataSource.getSimilarMovies(id)
            .map { it.toCoreModel() }
    }
}

package com.icgen.movieapp.remote

import com.icgen.movieapp.data.model.CastData
import com.icgen.movieapp.data.model.DetailData
import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.data.model.VideoData
import com.icgen.movieapp.data.source.detail.DetailApiDataSource
import com.icgen.movieapp.remote.mapper.toDataModel
import com.icgen.movieapp.remote.service.ApiService
import javax.inject.Inject

class DetailApiDataSourceImpl @Inject constructor(
    private val service: ApiService
) : DetailApiDataSource {

    override suspend fun getMovieDetail(id: Int): DetailData {
        return service.getMovieDetail(id)
            .toDataModel()
    }

    override suspend fun getMovieCast(id: Int): List<CastData> {
        return service.getMovieCredits(id)
            .cast.map { it.toDataModel() }
    }

    override suspend fun getMovieVideo(id: Int): List<VideoData> {
        return service.getMoviePreviews(id)
            .results.map { it.toDataModel() }
    }

    override suspend fun getSimilarMovies(id: Int): List<MovieData> {
        return service.getSimilarMovies(id)
            .results.map { it.toDataModel() }
    }
}
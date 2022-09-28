package com.icgen.movieapp.remote

import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.data.source.home.HomeApiDataSource
import com.icgen.movieapp.remote.mapper.toDataModel
import com.icgen.movieapp.remote.service.ApiService
import javax.inject.Inject

class HomeApiDataSourceImpl @Inject constructor(
    private val service: ApiService
) : HomeApiDataSource {

    override suspend fun getTrendingMovies(): List<MovieData> {
        return service.getTrendingMovies()
            .results.map { it.toDataModel() }
    }

    override suspend fun getPopularMovies(): List<MovieData> {
        return service.getPopularMovies()
            .results.map { it.toDataModel() }
    }

    override suspend fun getUpcomingMovies(): List<MovieData> {
        return service.getUpcomingMovies()
            .results.map { it.toDataModel() }
    }

    override suspend fun getTopRatedMovies(): List<MovieData> {
        return service.getTopRatedMovies()
            .results.map { it.toDataModel() }
    }
}

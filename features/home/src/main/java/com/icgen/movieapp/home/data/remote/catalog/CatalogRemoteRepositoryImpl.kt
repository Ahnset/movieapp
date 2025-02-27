package com.icgen.movieapp.home.data.remote.catalog

import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.home.data.mapper.toModel
import com.icgen.movieapp.home.data.remote.service.HomeApiService
import javax.inject.Inject

class CatalogRemoteRepositoryImpl @Inject constructor(
    private val service: HomeApiService
) : CatalogRemoteRepository {

    override suspend fun getTrendingMovies(): List<Movie> {
        return service.getTrendingMovies()
            .results.map { it.toModel() }
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return service.getPopularMovies()
            .results.map { it.toModel() }
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return service.getUpcomingMovies()
            .results.map { it.toModel() }
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return service.getTopRatedMovies()
            .results.map { it.toModel() }
    }
}

package com.icgen.movieapp.home.data.remote.catalog

import com.icgen.movieapp.common.domain.model.Movie

interface CatalogRemoteRepository {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getUpcomingMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
}
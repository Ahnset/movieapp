package com.icgen.movieapp.home.domain.repository

import com.icgen.movieapp.common.domain.model.Movie

interface CatalogRepository {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getUpcomingMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
}

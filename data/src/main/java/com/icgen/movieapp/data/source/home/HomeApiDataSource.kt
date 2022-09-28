package com.icgen.movieapp.data.source.home

import com.icgen.movieapp.data.model.MovieData

interface HomeApiDataSource {
    suspend fun getTrendingMovies(): List<MovieData>
    suspend fun getPopularMovies(): List<MovieData>
    suspend fun getUpcomingMovies(): List<MovieData>
    suspend fun getTopRatedMovies(): List<MovieData>
}

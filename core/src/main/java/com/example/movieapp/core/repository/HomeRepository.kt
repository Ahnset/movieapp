package com.example.movieapp.core.repository

import com.icgen.movieapp.core.model.Movie

interface HomeRepository {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getPopularMovies(): List<Movie>
    suspend fun getUpcomingMovies(): List<Movie>
    suspend fun getTopRatedMovies(): List<Movie>
}
package com.icgen.movieapp.remote.service

import com.icgen.movieapp.remote.dto.MovieApiDto
import retrofit2.http.GET

interface ApiService {

    @GET("/trending/movie/week")
    suspend fun getTrendingMovies(): MovieApiDto

    @GET("/movie/popular")
    suspend fun getPopularMovies(): MovieApiDto

    @GET("/movie/upcoming")
    suspend fun getUpcomingMovies(): MovieApiDto

    @GET("/movie/top_rated")
    suspend fun getTopRatedMovies(): MovieApiDto

}
package com.icgen.movieapp.remote.service

import com.icgen.movieapp.remote.dto.CreditDto
import com.icgen.movieapp.remote.dto.DetailDto
import com.icgen.movieapp.remote.dto.MovieApiDto
import com.icgen.movieapp.remote.dto.PreviewDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): MovieApiDto

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieApiDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieApiDto

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieApiDto

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): DetailDto

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(@Path("id") id: Int): CreditDto

    @GET("movie/{id}/videos")
    suspend fun getMoviePreviews(@Path("id") id: Int): PreviewDto

    @GET("movie/{id}/similar")
    suspend fun getSimilarMovies(@Path("id") id: Int): MovieApiDto
}

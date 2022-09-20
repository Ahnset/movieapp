package com.icgen.movieapp.data.source.home

import com.icgen.movieapp.data.model.MovieData

interface HomeRemoteSource {
    fun getTrendingMovies(): List<MovieData>
    fun getPopularMovies(): List<MovieData>
    fun getUpcomingMovies(): List<MovieData>
    fun getTopRatedMovies(): List<MovieData>
}

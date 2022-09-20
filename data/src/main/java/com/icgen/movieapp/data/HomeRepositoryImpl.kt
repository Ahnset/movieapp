package com.icgen.movieapp.data

import com.example.movieapp.core.repository.HomeRepository
import com.icgen.movieapp.core.model.Movie
import com.icgen.movieapp.data.mapper.toCoreModel
import com.icgen.movieapp.data.source.home.HomeRemoteSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val remoteSource: HomeRemoteSource
) : HomeRepository {

    override suspend fun getTrendingMovies(): List<Movie> {
        return remoteSource.getTrendingMovies()
            .map { movieData -> movieData.toCoreModel() }
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return remoteSource.getPopularMovies()
            .map { movieData -> movieData.toCoreModel() }
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return remoteSource.getUpcomingMovies()
            .map { movieData -> movieData.toCoreModel() }
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return remoteSource.getTopRatedMovies()
            .map { movieData -> movieData.toCoreModel() }
    }
}

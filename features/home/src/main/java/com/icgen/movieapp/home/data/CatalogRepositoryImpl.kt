package com.icgen.movieapp.home.data

import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.home.data.remote.catalog.CatalogRemoteRepository
import com.icgen.movieapp.home.domain.repository.CatalogRepository
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val remoteRepository: CatalogRemoteRepository
)  : CatalogRepository {
    override suspend fun getTrendingMovies(): List<Movie> {
        return remoteRepository.getTrendingMovies()
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return remoteRepository.getPopularMovies()
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        return remoteRepository.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        return remoteRepository.getTopRatedMovies()
    }


}
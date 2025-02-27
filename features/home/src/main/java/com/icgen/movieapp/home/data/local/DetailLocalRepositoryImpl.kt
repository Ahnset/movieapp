package com.icgen.movieapp.home.data.local

import com.icgen.movieapp.common.data.local.MovieDao
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.home.data.mapper.toEntity
import javax.inject.Inject

class DetailLocalRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : DetailLocalRepository {
    override suspend fun saveMovie(movie: Movie) {
        return movieDao.saveMovie(movie.toEntity())
    }
}
package com.icgen.movieapp.home.data.local

import com.icgen.movieapp.common.domain.model.Movie

interface DetailLocalRepository {
    suspend fun saveMovie(movie: Movie)
}
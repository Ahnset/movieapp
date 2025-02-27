package com.icgen.movieapp.home.data.remote.detail

import com.icgen.movieapp.common.domain.model.Cast
import com.icgen.movieapp.common.domain.model.Detail
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.model.Video

interface DetailRemoteRepository {
    suspend fun getMovieDetail(id: Int): Detail
    suspend fun getMovieCast(id: Int): List<Cast>
    suspend fun getMovieVideos(id: Int): List<Video>
    suspend fun getSimilarMovies(id: Int): List<Movie>
}
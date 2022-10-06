package com.example.movieapp.core.repository

import com.example.movieapp.core.model.Cast
import com.example.movieapp.core.model.Detail
import com.example.movieapp.core.model.Video
import com.icgen.movieapp.core.model.Movie

interface DetailRepository {
    suspend fun getMovieDetail(id: Int): Detail
    suspend fun getMovieCast(id: Int): List<Cast>
    suspend fun getMovieVideos(id: Int): List<Video>
    suspend fun getSimilarMovies(id: Int): List<Movie>
}

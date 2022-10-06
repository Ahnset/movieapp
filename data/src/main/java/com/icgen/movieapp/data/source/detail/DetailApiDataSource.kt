package com.icgen.movieapp.data.source.detail

import com.icgen.movieapp.data.model.CastData
import com.icgen.movieapp.data.model.DetailData
import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.data.model.VideoData

interface DetailApiDataSource {
    suspend fun getMovieDetail(id: Int): DetailData
    suspend fun getMovieCast(id: Int): List<CastData>
    suspend fun getMovieVideo(id: Int): List<VideoData>
    suspend fun getSimilarMovies(id: Int): List<MovieData>
}

package com.icgen.movieapp.data.mapper

import com.example.movieapp.core.model.Cast
import com.example.movieapp.core.model.Detail
import com.example.movieapp.core.model.Genre
import com.example.movieapp.core.model.Video
import com.icgen.movieapp.core.model.Movie
import com.icgen.movieapp.data.model.CastData
import com.icgen.movieapp.data.model.DetailData
import com.icgen.movieapp.data.model.GenreData
import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.data.model.VideoData

fun MovieData.toCoreModel() =
    Movie(id, title, posterPath)

fun GenreData.toCoreModel() =
    Genre(id, name)

fun DetailData.toCoreModel() =
    Detail(
        id,
        title,
        overview,
        genres.map { it.toCoreModel() },
        posterPath,
        popularity,
        voteAverage,
        voteCount,
        status,
        runtime,
        releaseDate
    )

fun CastData.toCoreModel() =
    Cast(id, name, profilePath)

fun VideoData.toCoreModel() =
    Video(id, type, key)

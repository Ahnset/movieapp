package com.icgen.movieapp.remote.mapper

import com.icgen.movieapp.data.model.*
import com.icgen.movieapp.remote.dto.*

fun MovieResultDto.toDataModel() =
    MovieData(id, title, posterPath)

fun CastDto.toDataModel() =
    CastData(id, name, profilePath)

fun VideoDto.toDataModel() =
    VideoData(id, type, key)

fun GenreDto.toDataModel() =
    GenreData(id, name)

fun DetailDto.toDataModel() =
    DetailData(
        id,
        title,
        overview,
        genres.map { it.toDataModel() },
        posterPath,
        popularity,
        voteAverage,
        voteCount,
        status,
        runtime,
        releaseDate
    )

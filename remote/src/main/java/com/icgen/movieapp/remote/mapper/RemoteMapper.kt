package com.icgen.movieapp.remote.mapper

import com.icgen.movieapp.data.model.CastData
import com.icgen.movieapp.data.model.DetailData
import com.icgen.movieapp.data.model.GenreData
import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.data.model.VideoData
import com.icgen.movieapp.remote.dto.CastDto
import com.icgen.movieapp.remote.dto.DetailDto
import com.icgen.movieapp.remote.dto.GenreDto
import com.icgen.movieapp.remote.dto.MovieResultDto
import com.icgen.movieapp.remote.dto.VideoDto

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

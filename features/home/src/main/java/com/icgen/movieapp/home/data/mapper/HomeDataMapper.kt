package com.icgen.movieapp.home.data.mapper

import com.icgen.movieapp.common.data.local.MovieEntity
import com.icgen.movieapp.common.data.remote.ApiHelper.IMAGE_PREFIX
import com.icgen.movieapp.common.domain.model.Cast
import com.icgen.movieapp.common.domain.model.Detail
import com.icgen.movieapp.common.domain.model.Genre
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.model.Video
import com.icgen.movieapp.home.data.remote.dto.CastDto
import com.icgen.movieapp.home.data.remote.dto.DetailDto
import com.icgen.movieapp.home.data.remote.dto.GenreDto
import com.icgen.movieapp.home.data.remote.dto.MovieResultDto
import com.icgen.movieapp.home.data.remote.dto.VideoDto

fun MovieResultDto.toModel() =
    Movie(id, title, "$IMAGE_PREFIX$posterPath")

fun CastDto.toModel() =
    Cast(id, name, "$IMAGE_PREFIX$profilePath")

fun VideoDto.toModel() =
    Video(id, type, key)

fun GenreDto.toModel() =
    Genre(id, name)

fun DetailDto.toModel() =
    Detail(
        id,
        title,
        overview,
        genres.map { it.toModel() },
        "$IMAGE_PREFIX$posterPath",
        popularity,
        voteAverage,
        voteCount,
        status,
        runtime,
        releaseDate
    )

fun Movie.toEntity() =
    MovieEntity(id, title, posterPath)
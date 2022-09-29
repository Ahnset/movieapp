package com.icgen.movieapp.remote.common

import com.icgen.movieapp.remote.dto.MovieApiDto
import com.icgen.movieapp.remote.dto.MovieResultDto
import com.icgen.movieapp.remote.dto.DetailDto
import com.icgen.movieapp.remote.dto.CreditDto
import com.icgen.movieapp.remote.dto.PreviewDto
import com.icgen.movieapp.remote.dto.CastDto
import com.icgen.movieapp.remote.dto.VideoDto
import com.icgen.movieapp.remote.dto.GenreDto
import java.util.UUID
import kotlin.random.Random

object TestHelper {

    fun makeMovieApiDto() =
        MovieApiDto(0, emptyList(), 0, 0)

    fun makeMovieResultDto() =
        MovieResultDto(
            id = Random.nextInt(0, 2),
            title = UUID.randomUUID().toString(),
            posterPath = UUID.randomUUID().toString(),
        )

    fun makeDetailDto() =
        DetailDto(
            id = Random.nextInt(0, 1),
            title = UUID.randomUUID().toString(),
            overview = UUID.randomUUID().toString(),
            genres = emptyList(),
            posterPath = UUID.randomUUID().toString(),
            popularity = Random.nextDouble(0.0, 1.0),
            voteAverage = Random.nextDouble(0.0, 1.0),
            voteCount = Random.nextInt(0, 1),
            status = UUID.randomUUID().toString(),
            runtime = Random.nextInt(0, 1),
            releaseDate = UUID.randomUUID().toString()
        )

    fun makeCreditDto() =
        CreditDto(
            id = Random.nextInt(0, 1),
            cast = emptyList()
        )

    fun makePreviewDto() =
        PreviewDto(
            id = Random.nextInt(0, 1),
            results = emptyList()
        )

    fun makeCastDto() =
        CastDto(
            id = Random.nextInt(0, 2),
            name = UUID.randomUUID().toString(),
            profilePath = UUID.randomUUID().toString(),
        )

    fun makeVideoDto() =
        VideoDto(
            id = Random.nextInt(0, 2),
            type = UUID.randomUUID().toString(),
            key = UUID.randomUUID().toString(),
        )

    fun makeGenreDto() =
        GenreDto(
            id = Random.nextInt(0, 2),
            name = UUID.randomUUID().toString(),
        )
}
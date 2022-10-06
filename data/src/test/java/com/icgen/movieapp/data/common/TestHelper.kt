package com.icgen.movieapp.data.common

import com.icgen.movieapp.data.model.CastData
import com.icgen.movieapp.data.model.DetailData
import com.icgen.movieapp.data.model.GenreData
import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.data.model.VideoData
import java.util.UUID
import kotlin.random.Random

object TestHelper {

    fun makeMovieData() =
        MovieData(
            id = Random.nextInt(0, 2),
            title = UUID.randomUUID().toString(),
            posterPath = UUID.randomUUID().toString()
        )

    fun makeCastData() =
        CastData(
            id = Random.nextInt(0, 2),
            name = UUID.randomUUID().toString(),
            profilePath = UUID.randomUUID().toString()
        )

    fun makeGenreData() =
        GenreData(
            id = Random.nextInt(0, 2),
            name = UUID.randomUUID().toString()
        )

    fun makeVideoData() =
        VideoData(
            id = UUID.randomUUID().toString(),
            type = UUID.randomUUID().toString(),
            key = UUID.randomUUID().toString(),
        )

    fun makeDetailData() =
        DetailData(
            id = Random.nextInt(0, 2),
            title = UUID.randomUUID().toString(),
            overview = UUID.randomUUID().toString(),
            genres = emptyList(),
            posterPath = UUID.randomUUID().toString(),
            popularity = 100.0,
            voteAverage = 10.0,
            voteCount = 1,
            status = UUID.randomUUID().toString(),
            runtime = 10,
            releaseDate = UUID.randomUUID().toString(),
        )
}

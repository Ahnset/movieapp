package com.example.movieapp.core.common

import com.example.movieapp.core.model.Detail
import com.example.movieapp.core.model.Video
import com.icgen.movieapp.core.model.Movie
import java.util.UUID
import kotlin.random.Random

object TestHelper {

    fun makeVideo() =
        Video(
            id = UUID.randomUUID().toString(),
            type = UUID.randomUUID().toString(),
            key = UUID.randomUUID().toString(),
        )

    fun makeDetail() =
        Detail(
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

    fun makeMovies(count: Int): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) { movies.add(makeMovie()) }
        return movies
    }

    private fun makeMovie() = Movie(
        id = Random.nextInt(0, 2),
        title = UUID.randomUUID().toString(),
        posterPath = UUID.randomUUID().toString(),
    )
}

package com.icgen.movieapp.home.util

import com.icgen.movieapp.common.domain.model.Cast
import com.icgen.movieapp.common.domain.model.Detail
import com.icgen.movieapp.common.domain.model.Movie
import com.icgen.movieapp.common.domain.model.Video
import com.icgen.movieapp.common.util.Dispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import java.util.UUID
import java.util.UUID.randomUUID
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
object TestHelper {
    private fun makeVideo() =
        Video(
            id = randomUUID().toString(),
            type = randomUUID().toString(),
            key = randomUUID().toString(),
        )

    fun makeVideos(count: Int): List<Video> {
        val videos = mutableListOf<Video>()
        repeat(count) { videos.add(makeVideo()) }
        return videos
    }

    private fun makeCast() =
        Cast(
            id = Random.nextInt(0, 1),
            name = randomUUID().toString(),
            profilePath = randomUUID().toString(),
        )

    fun makeCasts(count: Int): List<Cast> {
        val casts = mutableListOf<Cast>()
        repeat(count) { casts.add(makeCast()) }
        return casts
    }

    fun makeDetail() =
        Detail(
            id = Random.nextInt(0, 1),
            title = randomUUID().toString(),
            overview = randomUUID().toString(),
            genres = emptyList(),
            posterPath = randomUUID().toString(),
            popularity = Random.nextDouble(0.0, 1.0),
            voteAverage = Random.nextDouble(0.0, 1.0),
            voteCount = Random.nextInt(0, 1),
            status = randomUUID().toString(),
            runtime = Random.nextInt(0, 1),
            releaseDate = randomUUID().toString()
        )

    private fun makeMovie() = Movie(
        id = Random.nextInt(0, 2),
        title = randomUUID().toString(),
        posterPath = randomUUID().toString(),
    )

    fun makeMovies(count: Int): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) { movies.add(makeMovie()) }
        return movies
    }

    fun getTestDispatcher() = Dispatcher(
        io = UnconfinedTestDispatcher(),
        main = UnconfinedTestDispatcher(),
        default = UnconfinedTestDispatcher()
    )
}

package com.example.movieapp.core.common

import com.icgen.movieapp.core.model.Movie
import java.util.*
import kotlin.random.Random

object TestHelper {
    fun makeMovies(count: Int): List<Movie> {
        val movies = mutableListOf<Movie>()
        repeat(count) { movies.add(makeMovie()) }
        return movies
    }

    fun makeMovie() = Movie(
        id = Random.nextInt(0, 2),
        title = UUID.randomUUID().toString(),
        posterPath = UUID.randomUUID().toString(),
    )
}
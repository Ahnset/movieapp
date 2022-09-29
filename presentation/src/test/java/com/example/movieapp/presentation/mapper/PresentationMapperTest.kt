package com.example.movieapp.presentation.mapper

import com.example.movieapp.presentation.model.MovieUI
import com.icgen.movieapp.core.model.Movie
import org.junit.Assert
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class PresentationMapperTest {

    @Test
    fun `should map the correct movie`() {
        // Arrange
        val movieUI = makeMovieUI()
        val expected = Movie(
            movieUI.id,
            movieUI.title,
            movieUI.posterPath
        )

        // Act
        val test = movieUI.toCoreModel()

        // Assert
        Assert.assertEquals(expected, test)
    }

    @Test
    fun `should map the correct movie ui`() {
        // Arrange
        val movie = makeMovie()
        val expected = MovieUI(
            movie.id,
            movie.title,
            movie.posterPath
        )

        // Act
        val test = movie.toPresentationModel()

        // Assert
        Assert.assertEquals(expected, test)
    }

    private fun makeMovieUI() =
        MovieUI(
            id = Random.nextInt(0, 2),
            title = UUID.randomUUID().toString(),
            posterPath = UUID.randomUUID().toString(),
        )

    private fun makeMovie() =
        Movie(
            id = Random.nextInt(0, 2),
            title = UUID.randomUUID().toString(),
            posterPath = UUID.randomUUID().toString(),
        )
}

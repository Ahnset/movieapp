package com.icgen.movieapp.data.mapper

import com.icgen.movieapp.core.model.Movie
import com.icgen.movieapp.data.model.MovieData
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class DataMapperTest {

    @Test
    fun `should map the correct movie`() {
        // Arrange
        val movieData = makeMovieData()
        val expected = Movie(
            movieData.id,
            movieData.title,
            movieData.posterPath
        )

        // Act
        val test = movieData.toCoreModel()

        // Assert
        assertEquals(expected, test)
    }

    private fun makeMovieData() =
        MovieData(
            id = Random.nextInt(0, 2),
            title = UUID.randomUUID().toString(),
            posterPath = UUID.randomUUID().toString(),
        )
}

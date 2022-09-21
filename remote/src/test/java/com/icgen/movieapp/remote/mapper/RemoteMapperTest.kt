package com.icgen.movieapp.remote.mapper

import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.remote.dto.MovieResultDto
import org.junit.Assert
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class RemoteMapperTest {

    @Test
    fun `should map the correct movie result`() {
        // Arrange
        val movieResultDto = makeMovieResultDto()
        val expected = MovieData(
            movieResultDto.id,
            movieResultDto.title,
            movieResultDto.posterPath
        )

        // Act
        val test = movieResultDto.toDataModel()

        // Assert
        Assert.assertEquals(expected, test)
    }

    private fun makeMovieResultDto() =
        MovieResultDto(
            id = Random.nextInt(0, 2),
            title = UUID.randomUUID().toString(),
            posterPath = UUID.randomUUID().toString(),
        )
}

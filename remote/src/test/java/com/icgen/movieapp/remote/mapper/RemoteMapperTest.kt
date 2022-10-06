package com.icgen.movieapp.remote.mapper

import com.icgen.movieapp.data.model.CastData
import com.icgen.movieapp.data.model.DetailData
import com.icgen.movieapp.data.model.GenreData
import com.icgen.movieapp.data.model.MovieData
import com.icgen.movieapp.data.model.VideoData
import com.icgen.movieapp.remote.common.TestHelper.makeCastDto
import com.icgen.movieapp.remote.common.TestHelper.makeDetailDto
import com.icgen.movieapp.remote.common.TestHelper.makeGenreDto
import com.icgen.movieapp.remote.common.TestHelper.makeMovieResultDto
import com.icgen.movieapp.remote.common.TestHelper.makeVideoDto
import org.junit.Assert
import org.junit.Test

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

    @Test
    fun `should map the correct cast`() {
        // Arrange
        val castDto = makeCastDto()
        val expected = CastData(
            castDto.id,
            castDto.name,
            castDto.profilePath
        )

        // Act
        val test = castDto.toDataModel()

        // Assert
        Assert.assertEquals(expected, test)
    }

    @Test
    fun `should map the correct video`() {
        // Arrange
        val videoDto = makeVideoDto()
        val expected = VideoData(
            videoDto.id,
            videoDto.type,
            videoDto.key
        )

        // Act
        val test = videoDto.toDataModel()

        // Assert
        Assert.assertEquals(expected, test)
    }

    @Test
    fun `should map the correct genre`() {
        // Arrange
        val genreDto = makeGenreDto()
        val expected = GenreData(
            genreDto.id,
            genreDto.name
        )

        // Act
        val test = genreDto.toDataModel()

        // Assert
        Assert.assertEquals(expected, test)
    }

    @Test
    fun `should map the correct detail`() {
        // Arrange
        val detailDto = makeDetailDto()
        val expected = DetailData(
            detailDto.id,
            detailDto.title,
            detailDto.overview,
            detailDto.genres.map { it.toDataModel() },
            detailDto.posterPath,
            detailDto.popularity,
            detailDto.voteAverage,
            detailDto.voteCount,
            detailDto.status,
            detailDto.runtime,
            detailDto.releaseDate
        )

        // Act
        val test = detailDto.toDataModel()

        // Assert
        Assert.assertEquals(expected, test)
    }
}

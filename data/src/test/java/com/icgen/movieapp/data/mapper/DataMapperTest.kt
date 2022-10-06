package com.icgen.movieapp.data.mapper

import com.example.movieapp.core.model.Cast
import com.example.movieapp.core.model.Detail
import com.example.movieapp.core.model.Genre
import com.example.movieapp.core.model.Video
import com.icgen.movieapp.core.model.Movie
import com.icgen.movieapp.data.common.TestHelper.makeCastData
import com.icgen.movieapp.data.common.TestHelper.makeDetailData
import com.icgen.movieapp.data.common.TestHelper.makeGenreData
import com.icgen.movieapp.data.common.TestHelper.makeMovieData
import com.icgen.movieapp.data.common.TestHelper.makeVideoData
import org.junit.Assert.assertEquals
import org.junit.Test

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

    @Test
    fun `should map the correct genre`() {
        // Arrange
        val genreData = makeGenreData()
        val expected = Genre(
            genreData.id,
            genreData.name
        )

        // Act
        val test = genreData.toCoreModel()

        // Assert
        assertEquals(expected, test)
    }

    @Test
    fun `should map the correct cast`() {
        // Arrange
        val castData = makeCastData()
        val expected = Cast(
            castData.id,
            castData.name,
            castData.profilePath
        )

        // Act
        val test = castData.toCoreModel()

        // Assert
        assertEquals(expected, test)
    }

    @Test
    fun `should map the correct video`() {
        // Arrange
        val videoData = makeVideoData()
        val expected = Video(
            videoData.id,
            videoData.type,
            videoData.key
        )

        // Act
        val test = videoData.toCoreModel()

        // Assert
        assertEquals(expected, test)
    }

    @Test
    fun `should map the correct detail`() {
        // Arrange
        val detailData = makeDetailData()
        val expected = Detail(
            detailData.id,
            detailData.title,
            detailData.overview,
            detailData.genres.map { it.toCoreModel() },
            detailData.posterPath,
            detailData.popularity,
            detailData.voteAverage,
            detailData.voteCount,
            detailData.status,
            detailData.runtime,
            detailData.releaseDate
        )

        // Act
        val test = detailData.toCoreModel()

        // Assert
        assertEquals(expected, test)
    }
}

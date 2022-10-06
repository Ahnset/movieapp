package com.icgen.movieapp.remote

import com.icgen.movieapp.remote.common.TestHelper.makeCreditDto
import com.icgen.movieapp.remote.common.TestHelper.makeDetailDto
import com.icgen.movieapp.remote.common.TestHelper.makePreviewDto
import com.icgen.movieapp.remote.dto.MovieApiDto
import com.icgen.movieapp.remote.service.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailApiDataSourceImplTest {

    private val service: ApiService = mockk(relaxed = true)
    private val detailApiDataSourceImpl = DetailApiDataSourceImpl(service)

    @Test
    fun `should call service getMovieDetail() once when getMovieDetail() is called`() {
        return runTest {

            // Arrange
            val id = 0
            coEvery { service.getMovieDetail(id) } returns makeDetailDto()

            // Act
            detailApiDataSourceImpl.getMovieDetail(id)

            // Assert
            coVerify(exactly = 1) { service.getMovieDetail(id) }
        }
    }

    @Test
    fun `should call service getMovieCredits() once when getMovieCast() is called`() {
        return runTest {

            // Arrange
            val id = 0
            coEvery { service.getMovieCredits(id) } returns makeCreditDto()

            // Act
            detailApiDataSourceImpl.getMovieCast(id)

            // Assert
            coVerify(exactly = 1) { service.getMovieCredits(id) }
        }
    }

    @Test
    fun `should call service getMoviePreviews() once when getMovieVideo() is called`() {
        return runTest {

            // Arrange
            val id = 0
            coEvery { service.getMoviePreviews(id) } returns makePreviewDto()

            // Act
            detailApiDataSourceImpl.getMovieVideo(id)

            // Assert
            coVerify(exactly = 1) { service.getMoviePreviews(id) }
        }
    }

    @Test
    fun `should call service getSimilarMovies() once when getSimilarMovies() is called`() {
        return runTest {

            // Arrange
            val id = 0
            coEvery { service.getSimilarMovies(id) } returns makeMovieApiDto()

            // Act
            detailApiDataSourceImpl.getSimilarMovies(id)

            // Assert
            coVerify(exactly = 1) { service.getSimilarMovies(id) }
        }
    }

    private fun makeMovieApiDto() =
        MovieApiDto(0, emptyList(), 0, 0)
}

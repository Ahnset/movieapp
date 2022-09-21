package com.icgen.movieapp.remote

import com.icgen.movieapp.remote.dto.MovieApiDto
import com.icgen.movieapp.remote.service.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeApiDataSourceImplTest {

    private val service: ApiService = mockk(relaxed = true)
    private val homeApiDataSourceImpl = HomeApiDataSourceImpl(service)

    @Test
    fun `should call service getTrendingMovies() once when getTrendingMovies() is called`() {
        return runTest {

            // Arrange
            coEvery { service.getTrendingMovies() } returns makeMovieApiDto()

            // Act
            homeApiDataSourceImpl.getTrendingMovies()

            // Assert
            coVerify(exactly = 1) { service.getTrendingMovies() }
        }
    }

    @Test
    fun `should call service getPopularMovies() once when getPopularMovies() is called`() {
        return runTest {

            // Arrange
            coEvery { service.getPopularMovies() } returns makeMovieApiDto()

            // Act
            homeApiDataSourceImpl.getPopularMovies()

            // Assert
            coVerify(exactly = 1) { service.getPopularMovies() }
        }
    }

    @Test
    fun `should call service getUpcomingMovies() once when getUpcomingMovies() is called`() {
        return runTest {

            // Arrange
            coEvery { service.getUpcomingMovies() } returns makeMovieApiDto()

            // Act
            homeApiDataSourceImpl.getUpcomingMovies()

            // Assert
            coVerify(exactly = 1) { service.getUpcomingMovies() }
        }
    }

    @Test
    fun `should call service getTopRatedMovies() once when getTopRatedMovies() is called`() {
        return runTest {

            // Arrange
            coEvery { service.getTopRatedMovies() } returns makeMovieApiDto()

            // Act
            homeApiDataSourceImpl.getTopRatedMovies()

            // Assert
            coVerify(exactly = 1) { service.getTopRatedMovies() }
        }
    }

    private fun makeMovieApiDto() =
        MovieApiDto(0, emptyList(), 0, 0)
}

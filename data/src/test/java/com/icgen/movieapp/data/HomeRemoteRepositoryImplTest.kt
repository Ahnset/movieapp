package com.icgen.movieapp.data

import com.icgen.movieapp.data.source.home.HomeApiDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRemoteRepositoryImplTest {

    private val apiDataSource: HomeApiDataSource = mockk(relaxed = true)
    private val homeRepositoryImpl = HomeRepositoryImpl(apiDataSource)

    @Test
    fun `should call apiDataSource getTrendingMovies() once when getTrendingMovies() is called`() {
        return runTest {
            // Arrange
            coEvery { apiDataSource.getTrendingMovies() } returns emptyList()

            // Act
            homeRepositoryImpl.getTrendingMovies()

            // Assert
            coVerify(exactly = 1) { apiDataSource.getTrendingMovies() }
        }
    }

    @Test
    fun `should call apiDataSource getPopularMovies() once when getPopularMovies() is called`() {
        return runTest {
            // Arrange
            coEvery { apiDataSource.getPopularMovies() } returns emptyList()

            // Act
            homeRepositoryImpl.getPopularMovies()

            // Assert
            coVerify(exactly = 1) { apiDataSource.getPopularMovies() }
        }
    }

    @Test
    fun `should call apiDataSource getUpcomingMovies() once when getUpcomingMovies() is called`() {
        return runTest {
            // Arrange
            coEvery { apiDataSource.getUpcomingMovies() } returns emptyList()

            // Act
            homeRepositoryImpl.getUpcomingMovies()

            // Assert
            coVerify(exactly = 1) { apiDataSource.getUpcomingMovies() }
        }
    }

    @Test
    fun `should call apiDataSource getTopRatedMovies() once when getTopRatedMovies() is called`() {
        return runTest {
            // Arrange
            coEvery { apiDataSource.getTopRatedMovies() } returns emptyList()

            // Act
            homeRepositoryImpl.getTopRatedMovies()

            // Assert
            coVerify(exactly = 1) { apiDataSource.getTopRatedMovies() }
        }
    }
}

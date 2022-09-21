package com.icgen.movieapp.data

import com.icgen.movieapp.data.source.home.HomeRemoteSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeRemoteRepositoryImplTest {

    private val remoteSource: HomeRemoteSource = mockk(relaxed = true)
    private val homeRepositoryImpl = HomeRepositoryImpl(remoteSource)

    @Test
    fun `should call remoteSource getTrendingMovies() once when getTrendingMovies() is called`() {
        return runTest {
            // Arrange
            coEvery { remoteSource.getTrendingMovies() } returns emptyList()

            // Act
            homeRepositoryImpl.getTrendingMovies()

            // Assert
            coVerify(exactly = 1) { remoteSource.getTrendingMovies() }
        }
    }

    @Test
    fun `should call remoteSource getPopularMovies() once when getTrendingMovies() is called`() {
        return runTest {
            // Arrange
            coEvery { remoteSource.getPopularMovies() } returns emptyList()

            // Act
            homeRepositoryImpl.getPopularMovies()

            // Assert
            coVerify(exactly = 1) { remoteSource.getPopularMovies() }
        }
    }

    @Test
    fun `should call remoteSource getUpcomingMovies() once when getTrendingMovies() is called`() {
        return runTest {
            // Arrange
            coEvery { remoteSource.getUpcomingMovies() } returns emptyList()

            // Act
            homeRepositoryImpl.getUpcomingMovies()

            // Assert
            coVerify(exactly = 1) { remoteSource.getUpcomingMovies() }
        }
    }

    @Test
    fun `should call remoteSource getTopRatedMovies() once when getTrendingMovies() is called`() {
        return runTest {
            // Arrange
            coEvery { remoteSource.getTopRatedMovies() } returns emptyList()

            // Act
            homeRepositoryImpl.getTopRatedMovies()

            // Assert
            coVerify(exactly = 1) { remoteSource.getTopRatedMovies() }
        }
    }
}

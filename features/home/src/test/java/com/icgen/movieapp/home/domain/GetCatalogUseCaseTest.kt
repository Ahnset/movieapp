package com.icgen.movieapp.home.domain

import com.icgen.movieapp.home.domain.repository.CatalogRepository
import com.icgen.movieapp.home.domain.usecase.GetCatalogUseCase
import com.icgen.movieapp.home.domain.usecase.GetCatalogUseCase.Output
import com.icgen.movieapp.home.util.TestHelper.getTestDispatcher
import com.icgen.movieapp.home.util.TestHelper.makeMovies
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetCatalogUseCaseTest {

    private val repository: CatalogRepository = mockk(relaxed = true)
    private val testDispatcher = getTestDispatcher()

    private val popularMovies = makeMovies(2)
    private val trendingMovies = makeMovies(2)
    private val topRatedMovies = makeMovies(2)
    private val upcomingMovies = makeMovies(2)

    private val useCase = GetCatalogUseCase(repository, testDispatcher)

    @Test
    fun `getPopularMovies() getTrendingMovies() getTopRatedMovies() getUpcomingMovies() should be called exactly once when use case is executed`() {
        return runTest(testDispatcher.io) {
            // When
            useCase()

            // Then
            coVerify(exactly = 1) { repository.getPopularMovies() }
            coVerify(exactly = 1) { repository.getTrendingMovies() }
            coVerify(exactly = 1) { repository.getTopRatedMovies() }
            coVerify(exactly = 1) { repository.getUpcomingMovies() }
        }
    }

    @Test
    fun `Result_success() with the correct data is returned when use case is successfully executed`() {
        return runTest(testDispatcher.io) {
            // Given
            stubRepository()

            val expected = Result.success(
                Output(popularMovies, trendingMovies, topRatedMovies, upcomingMovies)
            )

            // When
            val result = useCase()

            // Then
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Result_error() is returned when an exception occurs`() {
        return runTest(testDispatcher.io) {
            // Given
            coEvery { repository.getPopularMovies() } throws Exception()

            // When
            val result = useCase()

            // Then
            assertTrue(result.isFailure)
        }
    }

    private fun stubRepository() {
        coEvery { repository.getPopularMovies() } returns popularMovies
        coEvery { repository.getTrendingMovies() } returns trendingMovies
        coEvery { repository.getTopRatedMovies() } returns topRatedMovies
        coEvery { repository.getUpcomingMovies() } returns upcomingMovies
    }
}
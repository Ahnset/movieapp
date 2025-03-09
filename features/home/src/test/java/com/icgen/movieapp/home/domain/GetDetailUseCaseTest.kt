package com.icgen.movieapp.home.domain

import com.icgen.movieapp.home.domain.repository.DetailRepository
import com.icgen.movieapp.home.domain.usecase.GetDetailUseCase
import com.icgen.movieapp.home.domain.usecase.GetDetailUseCase.Output
import com.icgen.movieapp.home.util.TestHelper.getTestDispatcher
import com.icgen.movieapp.home.util.TestHelper.makeCasts
import com.icgen.movieapp.home.util.TestHelper.makeDetail
import com.icgen.movieapp.home.util.TestHelper.makeMovies
import com.icgen.movieapp.home.util.TestHelper.makeVideos
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetDetailUseCaseTest {

    private val repository: DetailRepository = mockk(relaxed = true)
    private val testDispatcher = getTestDispatcher()

    private val detail = makeDetail()
    private val casts = makeCasts(2)
    private val videos = makeVideos(2)
    private val similarMovies = makeMovies(2)

    private val useCase = GetDetailUseCase(repository, testDispatcher)

    @Test
    fun `getMovieDetail() getMovieCast() getMovieVideos() getSimilarMovies() should be called exactly once when use case is executed`() {
        return runTest(testDispatcher.io) {
            // Given
            val id: Int = 0

            // When
            useCase(id)

            // Then
            coVerify(exactly = 1) { repository.getMovieDetail(id) }
            coVerify(exactly = 1) { repository.getMovieCast(id) }
            coVerify(exactly = 1) { repository.getMovieVideos(id) }
            coVerify(exactly = 1) { repository.getSimilarMovies(id) }
        }
    }

    @Test
    fun `Result_success() with the correct data is returned when use case is successfully executed`() {
        return runTest(testDispatcher.io) {
            // Given
            val id = 2
            stubRepository(id)
            val expected = Result.success(
                Output(detail, casts, videos, similarMovies)
            )

            // When
            val result = useCase(id)

            // Then
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Result_error() is returned when an exception occurs`() {
        return runTest(testDispatcher.io) {
            // When
            val result = useCase()

            // Then
            assertTrue(result.isFailure)
        }
    }

    private fun stubRepository(id: Int) {
        coEvery { repository.getMovieDetail(id) } returns detail
        coEvery { repository.getMovieCast(id) } returns casts
        coEvery { repository.getSimilarMovies(id) } returns similarMovies
        coEvery { repository.getMovieVideos(id) } returns videos
    }
}
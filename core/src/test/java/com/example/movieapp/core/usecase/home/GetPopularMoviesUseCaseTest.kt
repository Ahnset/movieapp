package com.example.movieapp.core.usecase.home

import com.example.movieapp.core.common.Dispatcher
import com.example.movieapp.core.common.TestHelper.makeMovies
import com.example.movieapp.core.repository.HomeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class GetPopularMoviesUseCaseTest {

    private val repository: HomeRepository = mockk(relaxed = true)
    private val testDispatcher = Dispatcher(
        io = UnconfinedTestDispatcher(),
        main = UnconfinedTestDispatcher(),
        default = UnconfinedTestDispatcher(),
    )

    private val useCase = GetPopularMoviesUseCase(repository, testDispatcher)

    @Test
    fun `should call getPopularMovies() when executed`() {
        return runTest(testDispatcher.io) {
            // Act
            useCase()

            // Assert
            coVerify(exactly = 1) { repository.getPopularMovies() }
        }
    }

    @Test
    fun `should return success with correct data when executed with success`() {
        return runTest(testDispatcher.io) {
            // Arrange
            val movies = makeMovies(2)
            coEvery { repository.getPopularMovies() } returns movies
            val expected = Result.success(movies)

            // Act
            val result = useCase()

            // Assert
            assertEquals(expected, result)
        }
    }

    @Test
    fun `should return failure when executed with exception`() {
        return runTest(testDispatcher.io) {
            // Arrange
            val exception = SocketTimeoutException()
            coEvery { repository.getPopularMovies() } throws exception
            val expected = Result.failure<SocketTimeoutException>(exception)

            // Act
            val result = useCase()

            // Assert
            assertEquals(expected, result)
        }
    }
}

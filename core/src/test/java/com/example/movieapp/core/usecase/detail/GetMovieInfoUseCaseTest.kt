package com.example.movieapp.core.usecase.detail

import com.example.movieapp.core.common.Dispatcher
import com.example.movieapp.core.common.TestHelper.makeDetail
import com.example.movieapp.core.model.Cast
import com.example.movieapp.core.model.Video
import com.example.movieapp.core.repository.DetailRepository
import com.icgen.movieapp.core.model.Movie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.net.SocketTimeoutException

@ExperimentalCoroutinesApi
class GetMovieInfoUseCaseTest {

    private val repository: DetailRepository = mockk(relaxed = true)
    private val testDispatcher = Dispatcher(
        io = UnconfinedTestDispatcher(),
        main = UnconfinedTestDispatcher(),
        default = UnconfinedTestDispatcher(),
    )

    private val useCase = GetMovieInfoUseCase(repository, testDispatcher)

    @Test
    fun `should call getMovieDetail(), getMovieCredit() & getMoviePreview() when executed`() {
        return runTest(testDispatcher.io) {
            // Arrange
            val id = 0

            // Act
            useCase(id)

            // Assert
            coVerify(exactly = 1) { repository.getMovieDetail(id) }
            coVerify(exactly = 1) { repository.getMovieCast(id) }
            coVerify(exactly = 1) { repository.getMovieVideos(id) }
            coVerify(exactly = 1) { repository.getSimilarMovies(id) }
        }
    }

    @Test
    fun `should return success with correct data when executed with success`() {
        return runTest(testDispatcher.io) {
            // Arrange
            val id = 0
            val detail = makeDetail()
            val cast = emptyList<Cast>()
            val video = emptyList<Video>()
            val movies = emptyList<Movie>()

            coEvery { repository.getMovieDetail(id) } returns detail
            coEvery { repository.getMovieCast(id) } returns cast
            coEvery { repository.getMovieVideos(id) } returns video
            coEvery { repository.getSimilarMovies(id) } returns movies

            val expected = Result.success(
                GetMovieInfoUseCase.Output(detail, cast, video, movies)
            )

            // Act
            val result = useCase(id)

            // Assert
            Assert.assertEquals(expected, result)
        }
    }

    @Test
    fun `should return failure when executed with exception`() {
        return runTest(testDispatcher.io) {
            // Arrange
            val id = 0
            val exception = SocketTimeoutException()
            coEvery { repository.getMovieDetail(id) } throws exception
            val expected = Result.failure<SocketTimeoutException>(exception)

            // Act
            val result = useCase(id)

            // Assert
            Assert.assertEquals(expected, result)
        }
    }
}

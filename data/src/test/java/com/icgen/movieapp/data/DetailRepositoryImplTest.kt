package com.icgen.movieapp.data

import com.icgen.movieapp.data.common.TestHelper.makeDetailData
import com.icgen.movieapp.data.source.detail.DetailApiDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailRepositoryImplTest {

    private val apiDataSource: DetailApiDataSource = mockk(relaxed = true)
    private val detailRepositoryImpl = DetailRepositoryImpl(apiDataSource)

    @Test
    fun `should call apiDataSource getMovieDetail() once when getMovieDetail() is called`() {
        return runTest {
            // Arrange
            val id = 0
            coEvery { apiDataSource.getMovieDetail(id) } returns makeDetailData()

            // Act
            detailRepositoryImpl.getMovieDetail(id)

            // Assert
            coVerify(exactly = 1) { apiDataSource.getMovieDetail(id) }
        }
    }

    @Test
    fun `should call apiDataSource getMovieCast() once when getMovieCast() is called`() {
        return runTest {
            // Arrange
            val id = 0
            coEvery { apiDataSource.getMovieCast(id) } returns emptyList()

            // Act
            detailRepositoryImpl.getMovieCast(id)

            // Assert
            coVerify(exactly = 1) { apiDataSource.getMovieCast(id) }
        }
    }

    @Test
    fun `should call apiDataSource getMovieVideo() once when getMovieVideo() is called`() {
        return runTest {
            // Arrange
            val id = 0
            coEvery { apiDataSource.getMovieVideo(id) } returns emptyList()

            // Act
            detailRepositoryImpl.getMovieVideos(id)

            // Assert
            coVerify(exactly = 1) { apiDataSource.getMovieVideo(id) }
        }
    }

    @Test
    fun `should call apiDataSource getSimilarMovies() once when getSimilarMovies() is called`() {
        return runTest {
            // Arrange
            val id = 0
            coEvery { apiDataSource.getSimilarMovies(id) } returns emptyList()

            // Act
            detailRepositoryImpl.getSimilarMovies(id)

            // Assert
            coVerify(exactly = 1) { apiDataSource.getSimilarMovies(id) }
        }
    }
}

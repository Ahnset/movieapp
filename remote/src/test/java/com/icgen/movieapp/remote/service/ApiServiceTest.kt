package com.icgen.movieapp.remote.service

import com.google.gson.Gson
import com.icgen.movieapp.remote.dto.MovieApiDto
import com.icgen.movieapp.remote.dto.MovieResultDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class ApiServiceTest {

    @get:Rule val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    @Test
    fun `should return popular movies when getPopularMovies() is called`() {
        return runTest {

            // Arrange
            val movieApiDto = makeMovieApiDto(0, "title", "poster")
            val response = Gson().toJson(movieApiDto)
            enqueueSuccessResponse(response)

            // Act
            val observer = apiService.getPopularMovies()

            // Assert
            assertEquals(movieApiDto, observer)
            assertEquals(1, observer.results.size)
            assertEquals(0, observer.results[0].id)
            assertEquals("title", observer.results[0].title)
            assertEquals("poster", observer.results[0].posterPath)
        }
    }

    @Test(expected = retrofit2.HttpException::class)
    fun `should throw exception when server code is not 200`() {
        return runTest {
            // Arrange
            enqueueErrorResponse()

            // Act
            apiService.getTrendingMovies()
        }
    }

    @Test
    fun `should hit the correct endpoint when getPopularMovies() is called`() {
        return runBlocking {
            // Arrange
            setupSuccessRequest()

            // Act
            apiService.getPopularMovies()

            // Assert
            assertEquals("/movie/popular", mockWebServer.takeRequest().path)
        }
    }

    @Test
    fun `should hit the correct endpoint when getTrendingMovies() is called`() {
        return runBlocking {
            // Arrange
            setupSuccessRequest()

            // Act
            apiService.getTrendingMovies()

            // Assert
            assertEquals("/trending/movie/week", mockWebServer.takeRequest().path)
        }
    }

    @Test
    fun `should hit the correct endpoint when getUpcomingMovies() is called`() {
        return runBlocking {
            // Arrange
            setupSuccessRequest()

            // Act
            apiService.getUpcomingMovies()

            // Assert
            assertEquals("/movie/upcoming", mockWebServer.takeRequest().path)
        }
    }

    @Test
    fun `should hit the correct endpoint when getTopRatedMovies() is called`() {
        return runBlocking {
            // Arrange
            setupSuccessRequest()

            // Act
            apiService.getTopRatedMovies()

            // Assert
            assertEquals("/movie/top_rated", mockWebServer.takeRequest().path)
        }
    }

    private fun setupSuccessRequest() {
        val movieApiDto = makeMovieApiDto(0, "title", "poster")
        val response = Gson().toJson(movieApiDto)
        enqueueSuccessResponse(response)
    }

    private fun enqueueSuccessResponse(response: String) {
        mockWebServer.enqueue(
            MockResponse().setBody(response)
                .setResponseCode(200)
        )
    }

    private fun enqueueErrorResponse() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(400)
        )
    }

    private fun makeMovieApiDto(id: Int, title: String, poster: String) =
        MovieApiDto(
            page = 0, totalPages = 0, totalResults = 0,
            results = listOf(MovieResultDto(id, title, poster))
        )
}

package com.example.movieapp.detail.redux

import com.example.movieapp.core.common.Dispatcher
import com.example.movieapp.core.redux.Store
import com.example.movieapp.core.usecase.detail.GetMovieInfoUseCase
import com.example.movieapp.detail.common.TestHelper.makeMovieInfoLoadedFromOutput
import com.example.movieapp.detail.common.TestHelper.makeMovieInfoOutput
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfo
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfoError
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfoLoaded
import com.example.movieapp.detail.redux.DetailState.GetMovieInfoStarted
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailMiddlewareTest {

    private val getMovieInfoUseCase: GetMovieInfoUseCase = mockk(relaxed = true)
    private val store: Store<DetailState, DetailAction> = mockk(relaxed = true)
    private val dispatcher = Dispatcher(
        io = UnconfinedTestDispatcher(),
        main = UnconfinedTestDispatcher(),
        default = UnconfinedTestDispatcher(),
    )

    private val detailMiddleware = DetailMiddleware(getMovieInfoUseCase, dispatcher)

    @Test
    fun `should dispatch GetMovieInfoError when action GetMovieInfo is error`() {
        runTest {
            // Arrange
            val id = 0
            val message = "test exception message"
            val state = GetMovieInfoStarted
            val action = GetMovieInfo(id)
            val expected = GetMovieInfoError(message)
            coEvery { getMovieInfoUseCase(id) } returns Result.failure(Exception(message))

            // Act
            detailMiddleware.dispatch(state, action, store)

            // Assert
            coVerify(exactly = 1) {
                store.dispatch(expected)
            }
        }
    }

    @Test
    fun `should dispatch GetMovieInfoLoaded() when action GetMovieInfo is success`() {
        runTest {
            // Arrange
            val id = 0
            val state = GetMovieInfoStarted
            val action = GetMovieInfo(id)
            val output = makeMovieInfoOutput()
            val expected: GetMovieInfoLoaded = makeMovieInfoLoadedFromOutput(output)
            coEvery { getMovieInfoUseCase(id) } returns Result.success(output)

            // Act
            detailMiddleware.dispatch(state, action, store)

            // Assert
            coVerify(exactly = 1) {
                store.dispatch(expected)
            }
        }
    }
}

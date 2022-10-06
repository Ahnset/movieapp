package com.example.movieapp.detail.redux

import com.example.movieapp.detail.common.TestHelper.makeMovieInfoLoadedAction
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfo
import com.example.movieapp.detail.redux.DetailAction.GetMovieInfoError
import com.example.movieapp.detail.redux.DetailState.GetMovieInfoStarted
import com.example.movieapp.detail.redux.DetailState.MovieInfoError
import com.example.movieapp.detail.redux.DetailState.MovieInfoLoaded
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailStateMachineTest {

    private val detailMiddleware: DetailMiddleware = mockk(relaxed = true)
    private val stateMachine = DetailStateMachine(detailMiddleware)
    private val testObserver = mutableListOf<DetailState>()

    @Before
    fun setup() {
        stateMachine.build()
    }

    @Test
    fun `should set state GetMovieInfoStarted when action GetMovieInfo is triggered`() {
        runTest {
            // Arrange
            val id = 0
            val action = GetMovieInfo(id)
            val expected = GetMovieInfoStarted

            val job = launch(UnconfinedTestDispatcher()) {
                stateMachine.getViewState()?.toList(testObserver)
            }

            // Act
            stateMachine.dispatch(action)

            // Assert
            Assert.assertEquals(expected, testObserver.last())

            // teardown
            job.cancel()
        }
    }

    @Test
    fun `should set state MovieInfoLoaded when action GetMovieInfoLoaded is triggered`() {
        runTest {
            // Arrange
            val action = makeMovieInfoLoadedAction()

            val expected = MovieInfoLoaded(
                action.detail,
                action.cast,
                action.videos,
                action.similarMovies
            )

            val job = launch(UnconfinedTestDispatcher()) {
                stateMachine.getViewState()?.toList(testObserver)
            }

            // Act
            stateMachine.dispatch(action)

            // Assert
            Assert.assertEquals(expected, testObserver.last())

            // Teardown
            job.cancel()
        }
    }

    @Test
    fun `should set state MovieInfoError when action GetMovieInfoError is triggered`() {
        runTest {
            // Arrange
            val message = "test message"
            val expected = MovieInfoError(message)

            val job = launch(UnconfinedTestDispatcher()) {
                stateMachine.getViewState()?.toList(testObserver)
            }

            // Act
            stateMachine.dispatch(GetMovieInfoError(message))

            // Assert
            Assert.assertEquals(expected, testObserver.last())

            // Teardown
            job.cancel()
        }
    }
}

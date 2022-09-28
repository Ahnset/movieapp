package com.example.movieapp.home.redux

import com.example.movieapp.home.redux.HomeAction.GetHomeCatalogs
import com.example.movieapp.home.redux.HomeAction.GetHomeCatalogsError
import com.example.movieapp.home.redux.HomeAction.GetHomeCatalogsLoaded
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeStateMachineTest {

    private val homeMiddleware: HomeMiddleware = mockk(relaxed = true)
    private val stateMachine = HomeStateMachine(homeMiddleware)
    private val testObserver = mutableListOf<HomeState>()

    @Before
    fun setup() {
        stateMachine.build()
    }

    @Test
    fun `should set state GetHomeCatalogsStarted when action GetHomeCatalogs is triggered`() {
        runTest {
            // Arrange
            val expected = HomeState.GetHomeCatalogsStarted
            val job = launch(UnconfinedTestDispatcher()) {
                stateMachine.getViewState()?.toList(testObserver)
            }

            // Act
            stateMachine.dispatch(GetHomeCatalogs)

            // Assert
            assertEquals(expected, testObserver.last())

            // Teardown
            job.cancel()
        }
    }

    @Test
    fun `should set state HomeCatalogsLoaded when action GetHomeCatalogsLoaded is triggered`() {
        runTest {
            // Arrange
            val expected = HomeState.HomeCatalogsLoaded(
                emptyList(), emptyList(), emptyList(), emptyList()
            )
            val job = launch(UnconfinedTestDispatcher()) {
                stateMachine.getViewState()?.toList(testObserver)
            }

            // Act
            stateMachine.dispatch(
                GetHomeCatalogsLoaded(
                    emptyList(), emptyList(), emptyList(), emptyList()
                )
            )

            // Assert
            assertEquals(expected, testObserver.last())

            // Teardown
            job.cancel()
        }
    }

    @Test
    fun `should set state HomeCatalogsError when action GetHomeCatalogsError is triggered`() {
        runTest {
            // Arrange
            val message = "test message"
            val expected = HomeState.HomeCatalogsError(message)
            val job = launch(UnconfinedTestDispatcher()) {
                stateMachine.getViewState()?.toList(testObserver)
            }

            // Act
            stateMachine.dispatch(GetHomeCatalogsError(message))

            // Assert
            assertEquals(expected, testObserver.last())

            // Teardown
            job.cancel()
        }
    }
}

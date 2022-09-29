package com.example.movieapp.home.redux

import com.example.movieapp.core.common.Dispatcher
import com.example.movieapp.core.redux.Store
import com.example.movieapp.core.usecase.home.GetHomeCatalogsUseCase
import com.example.movieapp.core.usecase.home.GetHomeCatalogsUseCase.Output
import com.example.movieapp.home.redux.HomeAction.GetHomeCatalogsError
import com.example.movieapp.home.redux.HomeAction.GetHomeCatalogsLoaded
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeMiddlewareTest {

    private val getHomeCatalogsUseCase: GetHomeCatalogsUseCase = mockk(relaxed = true)
    private val store: Store<HomeState, HomeAction> = mockk(relaxed = true)
    private val dispatcher = Dispatcher(
        io = UnconfinedTestDispatcher(),
        main = UnconfinedTestDispatcher(),
        default = UnconfinedTestDispatcher(),
    )

    private val homeMiddleware = HomeMiddleware(getHomeCatalogsUseCase, dispatcher)

    @Test
    fun `should dispatch GetHomeCatalogsError() when action GetHomeCatalogs is error`() {
        runTest {
            // Arrange
            val message = "test exception message"
            val state = HomeState.GetHomeCatalogsStarted
            val action = HomeAction.GetHomeCatalogs
            coEvery { getHomeCatalogsUseCase() } returns Result.failure(Exception(message))

            // Act
            homeMiddleware.dispatch(state, action, store)

            // Assert
            coVerify(exactly = 1) {
                store.dispatch(GetHomeCatalogsError(message))
            }
        }
    }

    @Test
    fun `should dispatch GetHomeCatalogsLoaded() when action GetHomeCatalogs is success`() {
        runTest {
            // Arrange
            val state = HomeState.GetHomeCatalogsStarted
            val action = HomeAction.GetHomeCatalogs
            val output = Output(emptyList(), emptyList(), emptyList(), emptyList())
            coEvery { getHomeCatalogsUseCase() } returns Result.success(output)

            // Act
            homeMiddleware.dispatch(state, action, store)

            // Assert
            coVerify(exactly = 1) {
                store.dispatch(
                    GetHomeCatalogsLoaded(
                        emptyList(),
                        emptyList(),
                        emptyList(),
                        emptyList()
                    )
                )
            }
        }
    }
}

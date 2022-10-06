package com.example.movieapp.home.redux

import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val stateMachine: HomeStateMachine = mockk(relaxed = true)
    private val client: OkHttpClient = mockk(relaxed = true)
    private val viewModel = HomeViewModel(stateMachine, client)

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `should call stateMachine dispatch() when start() is invoked`() {
        runTest {
            // Arrange
            val action = HomeAction.GetHomeCatalogs

            // Act
            viewModel.start()

            // Assert
            coVerify(exactly = 1) { stateMachine.dispatch(action) }
        }
    }

    @Test
    fun `should evict cache when retry() is invoked`() {
        runTest {
            // Arrange

            // Act
            viewModel.retry()

            // Assert
            verify(exactly = 1) { client.cache?.evictAll() }
        }
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }
}

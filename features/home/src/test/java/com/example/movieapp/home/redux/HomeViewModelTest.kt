package com.example.movieapp.home.redux

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val stateMachine: HomeStateMachine = mockk(relaxed = true)
    private val viewModel = HomeViewModel(stateMachine)

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `should call stateMachine dispatch() when start() is executed`() {
        runTest {
            // Arrange
            val action = HomeAction.GetHomeCatalogs

            // Act
            viewModel.start()

            // Assert
            coVerify(exactly = 1) { stateMachine.dispatch(action) }
        }
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }
}

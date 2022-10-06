package com.example.movieapp.detail.redux

import com.example.movieapp.detail.redux.DetailAction.GetMovieInfo
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
class DetailViewModelTest {

    private val stateMachine: DetailStateMachine = mockk(relaxed = true)
    private val viewModel = DetailViewModel(stateMachine)

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `should dispatch action GetMovieInfo when viewModel start() is executed`() {
        runTest {
            // Arrange
            val id = 0
            val expected = GetMovieInfo(id)

            // Act
            viewModel.start(id)

            // Assert
            coVerify(exactly = 1) { stateMachine.dispatch(expected) }
        }
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }
}

package com.icgen.movieapp.home.presentation.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.icgen.movieapp.home.presentation.detail.DetailViewState.DetailError
import com.icgen.movieapp.home.presentation.detail.DetailViewState.Loading
import com.icgen.movieapp.home.util.getDummyDetail
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun loading_isShown() {
        testRule.setContent {
            DetailScreen(state = Loading)
        }

        testRule.onNodeWithContentDescription("CircularProgress").assertIsDisplayed()
    }

    @Test
    fun movieDetail_isShown() {
        val title = "Fake title movie"
        testRule.setContent {
            DetailScreen(state = DetailViewState.DetailLoaded(getDummyDetail(title)))
        }

        testRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun error_isShown() {
        testRule.setContent {
            DetailScreen(state = DetailError(""))
        }

        testRule.onNodeWithText("Network Error").assertIsDisplayed()
    }
}
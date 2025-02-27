package com.icgen.movieapp.home.presentation.catalog

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.icgen.movieapp.home.presentation.catalog.CatalogState.CatalogError
import com.icgen.movieapp.home.presentation.catalog.CatalogState.Loading
import com.icgen.movieapp.home.util.getDummyCatalog
import org.junit.Rule
import org.junit.Test

class CatalogScreenTest {

    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun loading_isShown() {
        testRule.setContent {
            CatalogScreen(state = Loading, {}, {})
        }

        testRule.onNodeWithContentDescription("CircularProgress").assertIsDisplayed()
    }

    @Test
    fun catalog_isShown() {
        testRule.setContent {
            CatalogScreen(state = getDummyCatalog(), {}, {})
        }

        testRule.onNodeWithText("Anora").assertIsDisplayed()
    }

    @Test
    fun error_isShown() {
        testRule.setContent {
            CatalogScreen(state = CatalogError(""), {}, {})
        }

        testRule.onNodeWithText("Network Error").assertIsDisplayed()
    }
}
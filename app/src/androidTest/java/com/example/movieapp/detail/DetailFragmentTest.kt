package com.example.movieapp.detail

import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.movieapp.common.MockWebServerHelper.serverIsDone
import com.example.movieapp.common.MockWebServerHelper.setSuccessMockedResponse
import com.example.movieapp.common.TestHelper.NestedScrollTo
import com.example.movieapp.common.launchFragmentInHiltContainer
import com.example.movieapp.detail.ui.DetailFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.awaitility.kotlin.await
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class DetailFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var client: OkHttpClient
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer.start(8080)
    }

    @Test
    fun shouldLaunchFragmentInContainer() = runTest {
        // Arrange
        val movieId = 0
        val args = bundleOf("movieId" to movieId)
        setSuccessMockedResponse(mockWebServer, movieId)

        // Act
        launchFragmentInHiltContainer<DetailFragment>(args)
    }

    @Test
    fun shouldShowCorrectContentWhenDetailIsFetched() = runTest {
        // Arrange
        val movieId = 0
        val args = bundleOf("movieId" to movieId)
        val expectedTitle = "Orphan: First Kill"
        setSuccessMockedResponse(mockWebServer, movieId)

        // Act
        launchFragmentInHiltContainer<DetailFragment>(args)
        await.until(serverIsDone(client))

        // Assert
        onView(withId(R.id.detailContent))
            .check(matches(withEffectiveVisibility(VISIBLE)))
        onView(withId(R.id.movieTitle))
            .check(matches(withText(expectedTitle)))
    }

    @Test
    fun shouldShowSnackMessageWhenBookmarkButtonIsClicked() = runTest {
        // Arrange
        val movieId = 0
        val args = bundleOf("movieId" to movieId)
        val expectedText = "TODO:// Add Bookmark feature."
        setSuccessMockedResponse(mockWebServer, movieId)

        // Act
        launchFragmentInHiltContainer<DetailFragment>(args)
        await.until(serverIsDone(client))

        onView(withId(R.id.detailBookmarkButton))
            .perform(NestedScrollTo(), click())

        // Assert
        onView(withText(expectedText))
            .check(matches(withEffectiveVisibility(VISIBLE)))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}

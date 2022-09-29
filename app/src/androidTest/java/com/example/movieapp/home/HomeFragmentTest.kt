package com.example.movieapp.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.movieapp.common.MockWebServerHelper.setSuccessMockedResponse
import com.example.movieapp.common.launchFragmentInHiltContainer
import com.example.movieapp.home.ui.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.awaitility.kotlin.await
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Callable
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class HomeFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject lateinit var client: OkHttpClient
    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer.start(8080)
    }

    @Test
    fun assertHomeFragmentLaunchInContainer() = runTest {
        // Act
        launchFragmentInHiltContainer<HomeFragment>()
    }

    @Test
    fun assertLoaderIsShownWhenFetchingData(): Unit = runBlocking {
        // Arrange
        setSuccessMockedResponse(mockWebServer)

        // Act
        launchFragmentInHiltContainer<HomeFragment>()

        // Assert
        onView(withId(R.id.homeProgress))
            .check(matches(withEffectiveVisibility(VISIBLE)))
    }

    @Test
    fun assertLoaderIsGoneWhenDataIsFetched(): Unit = runBlocking {
        // Arrange
        setSuccessMockedResponse(mockWebServer)

        // Act
        launchFragmentInHiltContainer<HomeFragment>()
        await.until(homeIsReady())

        // Assert
        onView(withId(R.id.homeProgress))
            .check(matches(withEffectiveVisibility(GONE)))
    }

    @Test
    fun assertToastIsShownWhenSliderItemIsClicked(): Unit = runBlocking {
        // Arrange
        val expectedId = 616037
        setSuccessMockedResponse(mockWebServer)

        // Act
        launchFragmentInHiltContainer<HomeFragment>()
        await.until(homeIsReady())

        onView(withId(R.id.slider))
            .perform(click())

        // Assert
        onView(withText("TODO:// Add Detail Feature for Movie $expectedId"))
            .check(matches(withEffectiveVisibility(VISIBLE)))
    }

    private fun homeIsReady(): Callable<Boolean> {
        return Callable {
            client.dispatcher.runningCallsCount() == 0
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}

package com.example.movieapp.common

import com.example.movieapp.common.ApiConstants.TRENDING_RESPONSE_FILE
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.util.concurrent.TimeUnit

object MockWebServerHelper {

    fun setSuccessMockedResponse(mockWebServer: MockWebServer) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile(TRENDING_RESPONSE_FILE))
            }
        }
    }

    fun setErrorMockedResponse(mockWebServer: MockWebServer) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().throttleBody(1024, 35, TimeUnit.SECONDS)
            }
        }
    }
}

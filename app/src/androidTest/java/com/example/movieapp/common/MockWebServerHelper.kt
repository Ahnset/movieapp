package com.example.movieapp.common

import com.example.movieapp.common.ApiConstants.CREDITS_RESPONSE_FILE
import com.example.movieapp.common.ApiConstants.DETAIL_RESPONSE_FILE
import com.example.movieapp.common.ApiConstants.TRENDING_RESPONSE_FILE
import com.example.movieapp.common.ApiConstants.VIDEOS_RESPONSE_FILE
import com.example.movieapp.common.FileReader.readStringFromFile
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

object MockWebServerHelper {

    fun setSuccessMockedResponse(
        mockWebServer: MockWebServer,
        responseFile: String
    ) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(readStringFromFile(responseFile))
            }
        }
    }

    fun setSuccessMockedResponse(mockWebServer: MockWebServer, id: Int) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when {
                    (request.path == "/movie/$id") -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(readStringFromFile(DETAIL_RESPONSE_FILE))
                    }

                    (request.path == "/movie/$id/credits") -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(readStringFromFile(CREDITS_RESPONSE_FILE))
                    }

                    (request.path == "/movie/$id/videos") -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(readStringFromFile(VIDEOS_RESPONSE_FILE))
                    }

                    (request.path == "/movie/$id/similar") -> {
                        MockResponse()
                            .setResponseCode(200)
                            .setBody(readStringFromFile(TRENDING_RESPONSE_FILE))
                    }

                    else -> {
                        MockResponse().setResponseCode(404)
                    }
                }
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

    fun serverIsDone(client: OkHttpClient): Callable<Boolean> {
        return Callable {
            client.dispatcher.runningCallsCount() == 0
        }
    }
}

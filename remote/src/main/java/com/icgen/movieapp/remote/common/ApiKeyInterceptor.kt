package com.icgen.movieapp.remote.common

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val key: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val url = request.url
            .newBuilder()
            .addQueryParameter("api_key", key)
            .build()

        request = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}

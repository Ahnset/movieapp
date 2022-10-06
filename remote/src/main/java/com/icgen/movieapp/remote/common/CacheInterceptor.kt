package com.icgen.movieapp.remote.common

import com.icgen.movieapp.remote.common.ApiHelper.CACHE_CONTROL
import com.icgen.movieapp.remote.common.ApiHelper.CACHE_TIMEOUT
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_TIMEOUT, TimeUnit.MINUTES)
            .build()

        return response.newBuilder()
            .header(CACHE_CONTROL, cacheControl.toString())
            .build()
    }
}

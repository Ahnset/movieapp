package com.icgen.movieapp.remote.di

import android.content.Context
import com.icgen.movieapp.remote.BuildConfig
import com.icgen.movieapp.remote.common.ApiHelper.OKHTTP_MAX_CACHE_SIZE
import com.icgen.movieapp.remote.common.ApiKeyInterceptor
import com.icgen.movieapp.remote.common.CacheInterceptor
import com.icgen.movieapp.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class ApiModule {

    protected open val baseUrl = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val key = BuildConfig.API_KEY

        return OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor(BuildConfig.DEBUG))
            .addInterceptor(ApiKeyInterceptor(key))
            .addNetworkInterceptor(CacheInterceptor())
            .cache(provideCache(context))
            .build()
    }

    private fun provideLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()

        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return logging
    }

    private fun provideCache(context: Context): Cache? {
        var cache: Cache? = null

        try {
            cache = Cache(
                File(context.cacheDir, "http-cache"),
                OKHTTP_MAX_CACHE_SIZE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return cache
    }
}

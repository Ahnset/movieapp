package com.icgen.movieapp.common.di

import android.content.Context
import com.icgen.movieapp.common.BuildConfig
import com.icgen.movieapp.common.data.remote.ApiHelper
import com.icgen.movieapp.common.data.remote.ApiKeyInterceptor
import com.icgen.movieapp.common.data.remote.CacheInterceptor
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
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()

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
                ApiHelper.OKHTTP_MAX_CACHE_SIZE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return cache
    }
}
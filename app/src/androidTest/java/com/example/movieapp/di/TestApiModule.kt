package com.example.movieapp.di

import android.content.Context
import com.example.movieapp.common.ApiConstants.BASE_URL
import com.icgen.movieapp.remote.common.ApiHelper
import com.icgen.movieapp.remote.di.ApiModule
import com.icgen.movieapp.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@TestInstallIn(
    replaces = [ApiModule::class],
    components = [SingletonComponent::class]
)
class TestApiModule {

    private val baseUrl = BASE_URL

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
        return OkHttpClient.Builder()
            .addInterceptor(provideLoggingInterceptor())
            .cache(provideCache(context))
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
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

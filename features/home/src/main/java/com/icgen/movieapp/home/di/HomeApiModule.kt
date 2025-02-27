package com.icgen.movieapp.home.di

import com.icgen.movieapp.home.data.remote.service.HomeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeApiModule {

    @Singleton
    @Provides
    fun provideRestaurantsApiService(retrofit: Retrofit): HomeApiService =
        retrofit.create(HomeApiService::class.java)
}
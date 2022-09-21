package com.icgen.movieapp.remote.di

import com.icgen.movieapp.data.source.home.HomeRemoteSource
import com.icgen.movieapp.remote.HomeApiDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Binds
    abstract fun bindHomeRemoteSource(impl: HomeApiDataSourceImpl): HomeRemoteSource
}

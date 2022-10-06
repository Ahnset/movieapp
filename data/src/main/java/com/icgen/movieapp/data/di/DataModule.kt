package com.icgen.movieapp.data.di

import com.example.movieapp.core.repository.DetailRepository
import com.example.movieapp.core.repository.HomeRepository
import com.icgen.movieapp.data.DetailRepositoryImpl
import com.icgen.movieapp.data.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindDetailRepository(impl: DetailRepositoryImpl): DetailRepository
}

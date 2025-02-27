package com.icgen.movieapp.home.di

import com.icgen.movieapp.home.data.CatalogRepositoryImpl
import com.icgen.movieapp.home.data.DetailRepositoryImpl
import com.icgen.movieapp.home.data.local.DetailLocalRepository
import com.icgen.movieapp.home.data.local.DetailLocalRepositoryImpl
import com.icgen.movieapp.home.data.remote.catalog.CatalogRemoteRepository
import com.icgen.movieapp.home.data.remote.catalog.CatalogRemoteRepositoryImpl
import com.icgen.movieapp.home.data.remote.detail.DetailRemoteRepository
import com.icgen.movieapp.home.data.remote.detail.DetailRemoteRepositoryImpl
import com.icgen.movieapp.home.domain.repository.CatalogRepository
import com.icgen.movieapp.home.domain.repository.DetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataModule {

    @Binds
    abstract fun bindCatalogRemoteRepository(impl: CatalogRemoteRepositoryImpl): CatalogRemoteRepository

    @Binds
    abstract fun bindCatalogRepository(impl: CatalogRepositoryImpl): CatalogRepository

    @Binds
    abstract fun bindDetailRemoteRepository(impl: DetailRemoteRepositoryImpl): DetailRemoteRepository

    @Binds
    abstract fun bindDetailLocalRepository(impl: DetailLocalRepositoryImpl): DetailLocalRepository

    @Binds
    abstract fun bindDetailRepository(impl: DetailRepositoryImpl): DetailRepository
}

package com.icgen.movieapp.common.di

import android.content.Context
import com.icgen.movieapp.common.data.local.MovieDB
import com.icgen.movieapp.common.data.local.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDB): MovieDao {
        return db.movieDao
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDB {
        return MovieDB.getInstance(context)
    }
}
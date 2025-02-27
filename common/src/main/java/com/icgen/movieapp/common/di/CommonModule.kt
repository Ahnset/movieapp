package com.icgen.movieapp.common.di

import com.icgen.movieapp.common.util.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    fun provideDispatchers(): Dispatcher = Dispatcher(
        io = Dispatchers.IO,
        main = Dispatchers.Main,
        default = Dispatchers.Default,
    )
}

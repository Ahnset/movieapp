package com.example.movieapp.di

import com.example.movieapp.common.ApiConstants.BASE_URL
import com.icgen.movieapp.remote.di.ApiModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    replaces = [ApiModule::class],
    components = [SingletonComponent::class]
)
class TestApiModule : ApiModule() {
    override val baseUrl: String
        get() = BASE_URL
}

package com.globant.challenge6.login.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SupportModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcher() = Dispatchers.IO
}
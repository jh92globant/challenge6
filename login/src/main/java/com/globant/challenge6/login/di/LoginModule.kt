package com.globant.challenge6.login.di

import com.globant.challenge6.login.data.localStorage.dataStore.LoginPreferencesDataStore
import com.globant.challenge6.login.data.repository.LoginPreferenceRepositoryImpl
import com.globant.challenge6.login.domain.repository.LoginPreferenceRepository
import com.globant.challenge6.login.domain.usecase.LoginUserUC
import com.globant.challenge6.login.ui.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object LoginModule {

    @Provides
    @ViewModelScoped
    fun provideHomeRepository(
        loginPreferencesDataStore: LoginPreferencesDataStore,
    ): LoginPreferenceRepository = LoginPreferenceRepositoryImpl(loginPreferencesDataStore)

    @Provides
    @ViewModelScoped
    fun provideGenresUseCase(
        loginPreference: LoginPreferenceRepository
    ) = LoginUserUC(loginPreference)

    @Provides
    fun provideLoginViewModel(
        loginUserUC: LoginUserUC,
        coroutineDispatcher: CoroutineDispatcher
    ) = LoginViewModel(loginUserUC, coroutineDispatcher)
}
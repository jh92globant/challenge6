package com.globant.challenge6.login.di.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.globant.challenge6.login.data.localStorage.dataStore.LoginPreferencesDataStore
import com.globant.challenge6.login.di.datastore.DATA_STORE_USER_LOGGED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideUserLoggedDataStore(
        @Named(DATA_STORE_USER_LOGGED)
        userLoggedDataStore: DataStore<Preferences>
    ): LoginPreferencesDataStore {
        return LoginPreferencesDataStore(userLoggedDataStore)
    }
}
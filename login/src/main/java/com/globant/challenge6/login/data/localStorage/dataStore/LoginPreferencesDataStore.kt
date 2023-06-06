package com.globant.challenge6.login.data.localStorage.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.globant.challenge6.login.domain.exception.KeyDataStoreNotFoundException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class LoginPreferencesDataStore(
    private val loginPreferences: DataStore<Preferences>
) {

    suspend fun saveIsUserLogged(isLogged: Boolean) {
        loginPreferences.edit { editor ->
            editor[USER_LOGGED_KEY] = isLogged
        }
    }

    val isUserLogged = loginPreferences.data.map { preferences ->
        preferences[USER_LOGGED_KEY] ?: false
    }.catch {
        throw KeyDataStoreNotFoundException
    }
}

const val USER_LOGGED_DATA_STORE = "user_logged_preference_data_store"
private val USER_LOGGED_KEY = booleanPreferencesKey("id_user_logged")
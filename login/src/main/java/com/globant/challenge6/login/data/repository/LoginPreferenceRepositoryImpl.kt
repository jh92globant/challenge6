package com.globant.challenge6.login.data.repository

import com.globant.challenge6.login.data.localStorage.dataStore.LoginPreferencesDataStore
import com.globant.challenge6.login.domain.exception.KeyDataStoreNotFoundException
import com.globant.challenge6.login.domain.repository.LoginPreferenceRepository
import com.globant.challenge6.login.domain.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class LoginPreferenceRepositoryImpl(
    private val loginPreferencesDataStore: LoginPreferencesDataStore
): LoginPreferenceRepository {

    override fun isUserLogged() = flow<Result<Boolean>> {
        val userLogged = loginPreferencesDataStore.isUserLogged.first()
        emit(Result.Success(userLogged))
    }.catch {
        emit(Result.Failure(KeyDataStoreNotFoundException))
    }

    override suspend fun saveUserLogged(isUserLogged: Boolean) {
        loginPreferencesDataStore.saveIsUserLogged(isUserLogged)
    }
}
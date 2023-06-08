package com.globant.challenge6.login.domain.repository

import com.globant.challenge6.login.domain.Result
import kotlinx.coroutines.flow.Flow

interface LoginPreferenceRepository {

    fun isUserLogged(): Flow<Result<Boolean>>

    suspend fun saveUserLogged(isUserLogged: Boolean)
}
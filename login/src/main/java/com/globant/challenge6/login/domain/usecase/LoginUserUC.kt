package com.globant.challenge6.login.domain.usecase

import com.globant.challenge6.login.domain.repository.LoginPreferenceRepository

class LoginUserUC(
    private val loginPreferenceRepository: LoginPreferenceRepository
) {

    fun isUserLogged() = loginPreferenceRepository.isUserLogged()

    suspend fun saveUserLogged(isUserLogged: Boolean){
        loginPreferenceRepository.saveUserLogged(isUserLogged)
    }
}
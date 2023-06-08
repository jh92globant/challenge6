package com.globant.challenge6.login.domain.usecase

import com.globant.challenge6.login.domain.Result
import com.globant.challenge6.login.domain.getSuccess
import com.globant.challenge6.login.domain.repository.LoginPreferenceRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class LoginUserUCTest {

    @MockK
    lateinit var loginPreferenceRepository: LoginPreferenceRepository

    private lateinit var loginUserUC: LoginUserUC

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        loginUserUC = LoginUserUC(loginPreferenceRepository)
    }

    @Test
    fun isUserLogged() {
        val isUserLogged = flowOf(Result.Success(true))
        every { loginPreferenceRepository.isUserLogged() } returns isUserLogged

        val result = loginUserUC.isUserLogged()

        runBlocking {
            assert(result.first().getSuccess() == true)
        }

        verify(exactly = 1) {
            loginPreferenceRepository.isUserLogged()
        }
        confirmVerified(loginPreferenceRepository)
    }

    @Test
    fun saveUserLogged() = runBlocking {
        coEvery {
            loginPreferenceRepository.saveUserLogged(true)
        } just Runs

        loginUserUC.saveUserLogged(true)

        coVerify(exactly = 1) {
            loginPreferenceRepository.saveUserLogged(true)
        }
        confirmVerified(loginPreferenceRepository)
    }
}
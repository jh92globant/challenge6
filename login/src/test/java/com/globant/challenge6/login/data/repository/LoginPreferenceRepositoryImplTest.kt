package com.globant.challenge6.login.data.repository

import com.globant.challenge6.login.data.localStorage.dataStore.LoginPreferencesDataStore
import com.globant.challenge6.login.domain.exception.KeyDataStoreNotFoundException
import com.globant.challenge6.login.domain.getFailure
import com.globant.challenge6.login.domain.getSuccess
import com.globant.challenge6.login.domain.isFailure
import com.globant.challenge6.login.domain.isSuccess
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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoginPreferenceRepositoryImplTest {

    @MockK(relaxed = true)
    lateinit var loginPreferences: LoginPreferencesDataStore

    private lateinit var loginPreferenceRepository: LoginPreferenceRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        loginPreferenceRepository = LoginPreferenceRepositoryImpl(loginPreferences)
    }

    @Test
    fun isUserLogged() = runBlocking {
        every { loginPreferences.isUserLogged } returns flowOf(true)

        val isUserLogged = loginPreferenceRepository.isUserLogged()
        isUserLogged.collect {
            Assert.assertEquals(it.isSuccess(), true)
            Assert.assertEquals(it.getSuccess(), true)
        }

        verify(exactly = 1) {
            loginPreferences.isUserLogged
        }
        confirmVerified(loginPreferences)
    }

    @Test
    fun isUserLoggedFail() = runBlocking {
        every { loginPreferences.isUserLogged } throws KeyDataStoreNotFoundException

        val isUserLogged = loginPreferenceRepository.isUserLogged()
        isUserLogged.collect {
            Assert.assertEquals(it.isFailure(), true)
            Assert.assertEquals(it.getFailure(), KeyDataStoreNotFoundException)
        }

        verify(exactly = 1) {
            loginPreferences.isUserLogged
        }
        confirmVerified(loginPreferences)
    }

    @Test
    fun saveUserLogged() = runBlocking {
        coEvery { loginPreferences.saveIsUserLogged(false) } just Runs

        loginPreferences.saveIsUserLogged(false)

        coVerify {
            loginPreferences.saveIsUserLogged(false)
        }
        confirmVerified(loginPreferences)
    }
}
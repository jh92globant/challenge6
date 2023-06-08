package com.globant.challenge6.login.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.challenge6.login.domain.Result
import com.globant.challenge6.login.domain.usecase.LoginUserUC
import com.globant.challenge6.login.util.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var loginUserUC: LoginUserUC
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        loginViewModel = LoginViewModel(loginUserUC, mainCoroutineRule.dispatcher)
    }

    @Test
    fun isUserLogged() = runTest {
        val isUserLogged = flowOf(Result.Success(true))
        val results = arrayListOf<Boolean?>()

        coEvery { loginUserUC.isUserLogged() } returns isUserLogged

        val job = launch(mainCoroutineRule.dispatcher) {
            loginViewModel.userLoggedState.toList(results)
        }

        loginViewModel.isUserLogged()

        val result = loginViewModel.userLoggedState.value

        assertNotNull(results[0])
        assertEquals(results[0], result)

        coVerify(exactly = 1) { loginUserUC.isUserLogged() }
        confirmVerified(loginUserUC)

        job.cancel()
    }

    @Test
    fun saveUserLogged() {
        coEvery { loginUserUC.saveUserLogged(false) } just Runs

        loginViewModel.saveUserLogged(false)

        coVerify {
            loginUserUC.saveUserLogged(false)
        }
        confirmVerified(loginUserUC)
    }
}
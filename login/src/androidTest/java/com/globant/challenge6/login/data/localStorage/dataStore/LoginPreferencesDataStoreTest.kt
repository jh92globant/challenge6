package com.globant.challenge6.login.data.localStorage.dataStore

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.globant.challenge6.login.util.DataStoreTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class LoginPreferencesDataStoreTest : DataStoreTest() {

    private lateinit var loginPreferencesDataStore: LoginPreferencesDataStore

    @Before
    fun setUp() {
        loginPreferencesDataStore = LoginPreferencesDataStore(dataStore)
    }

    @Test
    @SmallTest
    fun saveIsUserLogged() = coTest {
        loginPreferencesDataStore.saveIsUserLogged(true)

        assertTrue(loginPreferencesDataStore.isUserLogged.first())
    }

    @Test
    @SmallTest
    fun isUserLoggedWithDefaultValue() = coTest {
        assertFalse(loginPreferencesDataStore.isUserLogged.first())
    }
}
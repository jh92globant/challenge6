package com.globant.challenge6.login.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import org.junit.After
import org.junit.Before
import java.io.File

abstract class DataStoreTest: CoroutineTest() {

    private lateinit var preferencesScope: CoroutineScope
    protected lateinit var dataStore: DataStore<Preferences>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun createDataStore() {
        preferencesScope = CoroutineScope(testDispatcher + Job())
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        dataStore = PreferenceDataStoreFactory.create(scope = preferencesScope) {
            context.preferencesDataStoreFile(
                "test-preferences-file"
            )
        }
    }

    @After
    fun removeDataStore() {
        File(
            ApplicationProvider.getApplicationContext<Context>().filesDir,
            "datastore"
        ).deleteRecursively()

        preferencesScope.cancel()
    }
}
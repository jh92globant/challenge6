package com.globant.challenge6.login.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before

@OptIn(ExperimentalCoroutinesApi::class)
abstract class CoroutineTest {

    protected val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val testCoroutineScope = TestScope(testDispatcher)

    @Before
    fun setUpViewModelScope() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUpViewModelScope() {
        Dispatchers.resetMain()
    }

    fun coTest(block: suspend TestScope.() -> Unit) =
        testCoroutineScope.runTest { block }
}
package com.globant.challenge6.login.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.challenge6.login.domain.fold
import com.globant.challenge6.login.domain.usecase.LoginUserUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUC: LoginUserUC,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _userLoggedState: MutableStateFlow<Boolean?> =
        MutableStateFlow(false)
    val userLoggedState: StateFlow<Boolean?> = _userLoggedState.asStateFlow()

    fun isUserLogged() {
        loginUserUC.isUserLogged().map { result ->
            result.fold(
                onSuccess = {
                    _userLoggedState.update { it }
                },
                onFailure = {
                    _userLoggedState.update { null }
                }
            )
        }
            .flowOn(coroutineDispatcher)
            .launchIn(viewModelScope)
    }

    fun saveUserLogged(isUserLogged: Boolean) {
        viewModelScope.launch {
            withContext(coroutineDispatcher) {
                loginUserUC.saveUserLogged(isUserLogged)
            }
        }
    }
}
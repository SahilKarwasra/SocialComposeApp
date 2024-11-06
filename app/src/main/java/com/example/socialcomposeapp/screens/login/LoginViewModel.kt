package com.example.socialcomposeapp.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialcomposeapp.screens.signup.SignUpEvent
import com.example.socialcomposeapp.screens.signup.SignUpNavigationEvent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _navigationState = MutableSharedFlow<LoginNavigationEvent>()
    val navigationState = _navigationState.asSharedFlow()

    private val uiEvent = MutableStateFlow<LoginEvent>(LoginEvent.Normal)
    val uiState = uiEvent.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onEmailChange(email: String) {
        _email.value = email
        validate()
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        validate()
    }

    private val _buttonEnabled = MutableStateFlow(false)
    val buttonEnabled = _buttonEnabled.asStateFlow()

    private fun validate() {
        _buttonEnabled.value = email.value.isNotEmpty() &&
                password.value.isNotEmpty()
    }

    fun onLoginClicked() {
        uiEvent.value = LoginEvent.Loading
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    uiEvent.value = LoginEvent.Success
                    viewModelScope.launch {
                        _navigationState.emit(LoginNavigationEvent.NavigateToHome)
                    }
                } else {
                    uiEvent.value = LoginEvent.Error(result.exception?.message ?: "Unknown error")
                }
            }
    }

    fun onRegisterClicked() {
        viewModelScope.launch {
            _navigationState.emit(LoginNavigationEvent.NavigateToSignUp)
        }
    }


}


sealed class LoginEvent {
    object Normal : LoginEvent()
    object Loading : LoginEvent()
    data class Error(val message: String) : LoginEvent()
    object Success : LoginEvent()
}

sealed class LoginNavigationEvent {
    object NavigateToHome : LoginNavigationEvent()
    object NavigateToSignUp : LoginNavigationEvent()
}
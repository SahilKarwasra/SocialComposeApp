package com.example.socialcomposeapp.screens.userprofile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialcomposeapp.data.model.UserModel
import com.example.socialcomposeapp.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ProfileScreenViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserModel?>(null)
    val user: StateFlow<UserModel?> get() = _user

    val isLoading = mutableStateOf(true)

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            userRepository.getCurrentUserFromFireStore { user, success ->
                if (success && user != null) {
                    _user.value = user
                } else {
                    // Handle failure case if needed
                    _user.value = null
                }
                isLoading.value = false
            }
        }
    }
}


package com.example.socialcomposeapp.screens.editprofile


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialcomposeapp.data.model.UserModel
import com.example.socialcomposeapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _name = MutableStateFlow(TextFieldValue(""))
    val name: StateFlow<TextFieldValue> get() = _name

    private val _email = MutableStateFlow(TextFieldValue(""))
    val email: StateFlow<TextFieldValue> get() = _email

    private val _bio = MutableStateFlow(TextFieldValue(""))
    val bio: StateFlow<TextFieldValue> get() = _bio

    val isLoading = mutableStateOf(true)

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            userRepository.getCurrentUserFromFirestore { user, success ->
                if (success && user != null) {
                    _name.value = TextFieldValue(user.displayName ?: "")
                    _email.value = TextFieldValue(user.email ?: "")
                    _bio.value = TextFieldValue(user.bio ?: "")
                }
                isLoading.value = false
            }
        }
    }

    fun onNameChange(newName: TextFieldValue) {
        _name.value = newName
    }

    fun onEmailChange(newEmail: TextFieldValue) {
        _email.value = newEmail
    }

    fun onBioChange(newBio: TextFieldValue) {
        _bio.value = newBio
    }

    fun saveUserData() {
        viewModelScope.launch {
            val updatedUser = UserModel(
                displayName = _name.value.text,
                email = _email.value.text,
                bio = _bio.value.text,
                userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
            )
            userRepository.addUserToFirestore(updatedUser) { success ->
                // Handle success or failure
            }
        }
    }
}


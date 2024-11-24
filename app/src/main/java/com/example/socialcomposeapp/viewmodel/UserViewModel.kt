package com.example.socialcomposeapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.socialcomposeapp.data.model.UserModel
import com.example.socialcomposeapp.data.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // Adding User
    fun addUser(user: UserModel, onComplete: (Boolean) -> Unit) {
        userRepository.addUserToFireStore(user) { success ->
            onComplete(success)
        }
    }


}

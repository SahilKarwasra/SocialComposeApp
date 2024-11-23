package com.example.socialcomposeapp.screens.publicprofile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialcomposeapp.data.model.UserModel
import com.example.socialcomposeapp.data.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PublicProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    val isFollowing = mutableStateOf(false)
    val user = mutableStateOf<UserModel?>(null)

    fun fetchUser(userId: String) {
        viewModelScope.launch {
            user.value = userRepository.getUserById(userId)
        }
    }

    fun toggleFollow(currentUserId: String, targetUserId: String) {
        viewModelScope.launch {
            if (isFollowing.value) {
                val success = userRepository.unfollowUser(currentUserId, targetUserId)
                if (success) isFollowing.value = false
                fetchUser(targetUserId)
            } else {
                val success = userRepository.followUser(currentUserId, targetUserId)
                if (success) isFollowing.value = true
                fetchUser(targetUserId)
            }
        }
    }



    fun checkIfFollowing(currentUserId: String, targetUserId: String) {
        viewModelScope.launch {
            val db = FirebaseFirestore.getInstance()

            val doc = db.collection("users")
                .document(currentUserId)
                .collection("following")
                .document(targetUserId)
                .get()
                .await()

            isFollowing.value = doc.exists()
        }
    }
}

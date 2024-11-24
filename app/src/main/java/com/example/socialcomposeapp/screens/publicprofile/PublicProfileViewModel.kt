package com.example.socialcomposeapp.screens.publicprofile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialcomposeapp.data.model.UserModel
import com.example.socialcomposeapp.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

data class UserDate(
    val user: UserModel? = null,
    val isLoading : Boolean = false,
    val isFollowed: Boolean = false,
)

class PublicProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    private val _userState = MutableStateFlow<UserDate>(UserDate())
    val user = _userState.asStateFlow()


    fun fetchUser(userId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _userState.value = _userState.value.copy(
                    isLoading = true
                )

                val data = userRepository.getUserById(userId)
                val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

                val isFollowed = if(currentUserId != null && data?.userId != null){
                    checkIfFollowing(currentUserId, data.userId)
                }else{
                    false
                }

                try {
                    _userState.value = _userState.value.copy(
                        isLoading = false,
                        user = data,
                        isFollowed = isFollowed
                    )

                }catch (e: Exception){
                    Log.d("PublicProfileViewModel", "error: ${e.message}")
                }

            }
        }
    }

    fun toggleFollow(targetUserId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
                val followersCount = _userState.value.user?.followersCount
                if(currentUserId != null && followersCount != null){
                    if (user.value.isFollowed) {
                        val success = userRepository.unfollowUser(currentUserId, targetUserId)
                        if (success) _userState.value = _userState.value.copy(
                            isFollowed = false,
                            user = _userState.value.user?.copy(
                                followersCount = followersCount - 1
                            )
                        )
                    } else {
                        val success = userRepository.followUser(currentUserId, targetUserId)


                        if (success) {
                            _userState.value = _userState.value.copy(
                                isFollowed = true,
                                user = _userState.value.user?.copy(
                                    followersCount = followersCount + 1
                                )
                            )
                        }

                    }
                }
            }
        }
    }




    suspend fun checkIfFollowing(currentUserId: String, targetUserId: String) = withContext(Dispatchers.IO) {
        val db = FirebaseFirestore.getInstance()

        val doc = db.collection("users")
            .document(currentUserId)
            .collection("following")
            .document(targetUserId)
            .get()
            .await()

        return@withContext doc.exists()

    }
}

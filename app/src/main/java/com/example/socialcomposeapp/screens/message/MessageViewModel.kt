package com.example.socialcomposeapp.screens.message

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.socialcomposeapp.data.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MessageViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()


    private val uiEvent = MutableStateFlow<MessageEvent>(MessageEvent.Normal)
    val uiState = uiEvent.asStateFlow()

    private val _users = MutableStateFlow<List<UserModel>>(emptyList())
    val users = _users.asStateFlow()



    fun getUserData(userId: String) {
        uiEvent.value = MessageEvent.Loading
        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val displayName = document.getString("displayName") ?: ""
                    _userName.value = displayName
                    uiEvent.value = MessageEvent.Success
                    Log.d("username", "getUserData: $displayName")
                } else {
                    uiEvent.value = MessageEvent.Error("User not found.")
                }
            }
            .addOnFailureListener { exception ->
                uiEvent.value = MessageEvent.Error(exception.message ?: "Error fetching user data.")
            }
    }

    fun fetchAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            uiEvent.value = MessageEvent.Loading
            try {
                val userList = firestore.collection("users")
                    .get()
                    .await()
                    .toObjects(UserModel::class.java)

                _users.value = userList
                uiEvent.value = MessageEvent.Success
            } catch (e: Exception) {
                uiEvent.value = MessageEvent.Error("Failed to load users: ${e.message}")
            }
        }
    }

}

sealed class MessageEvent {
    object Normal : MessageEvent()
    object Loading : MessageEvent()
    data class Error(val message: String) : MessageEvent()
    object Success : MessageEvent()
}
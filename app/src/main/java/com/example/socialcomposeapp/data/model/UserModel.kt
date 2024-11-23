package com.example.socialcomposeapp.data.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class UserModel(
    val userId: String = "",
    val email: String = "",
    val displayName: String = "",
    val profilePictureUrl: String = "",
    val bio: String = "",
    val followersCount: Int = 0,
    val followingCount: Int = 0,
    val postsCount: Int = 0,
    val following: MutableList<String> = mutableListOf(),
    @ServerTimestamp val timestamp: Date? = null
)
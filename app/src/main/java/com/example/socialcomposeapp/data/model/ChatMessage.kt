package com.example.socialcomposeapp.data.model

data class ChatMessage(
    val text: String,
    val isSentByUser: Boolean,
    val timestamp: String
)
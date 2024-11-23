package com.example.socialcomposeapp.screens.chat.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.screens.chat.chatMessages

@Composable
fun MessageList() {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        items(chatMessages) { message ->
            MessageBubble(
                messageText = message.text,
                isSentByUser = message.isSentByUser,
                timestamp = message.timestamp
            )
        }
    }
}

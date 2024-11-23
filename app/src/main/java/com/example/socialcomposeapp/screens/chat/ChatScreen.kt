package com.example.socialcomposeapp.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.data.model.ChatMessage
import com.example.socialcomposeapp.screens.chat.components.ChatHeader
import com.example.socialcomposeapp.screens.chat.components.MessageInputBar
import com.example.socialcomposeapp.screens.chat.components.MessageList

@Composable
fun ChatScreen() {
    Box(
        contentAlignment = Alignment.BottomEnd,// Background color for chat screen
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3D013F)).padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ChatHeader()
            HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.7f))
            MessageList()
        }
        MessageInputBar()
    }

}

@Preview
@Composable
fun PreviewChatScreen() {
    ChatScreen()
}

val chatMessages = listOf(
    ChatMessage("Hello!", isSentByUser = true, timestamp = "08:21"),
    ChatMessage("Hi, how are you?", isSentByUser = false, timestamp = "08:21"),
    ChatMessage("I'm good, thanks!", isSentByUser = true, timestamp = "08:22"),
    ChatMessage("What about you?", isSentByUser = false, timestamp = "08:22")
)
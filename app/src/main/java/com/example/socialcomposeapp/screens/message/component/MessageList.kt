package com.example.socialcomposeapp.screens.message.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.data.model.Message
import com.example.socialcomposeapp.data.model.UserModel

@Composable
fun MessageList(users: List<UserModel>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(users) { user ->
            MessageItem(
                message = Message(
                    text = "Hello from ${user.displayName}",  // Example message content
                    time = "00:21",
                    profileImageRes = user.profilePictureUrl  // Adjust based on user model
                )
            )
        }
    }
}

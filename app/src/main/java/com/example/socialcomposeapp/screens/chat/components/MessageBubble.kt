package com.example.socialcomposeapp.screens.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MessageBubble(messageText: String, isSentByUser: Boolean, timestamp: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = if (isSentByUser) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Text(text = messageText)
        }
        Text(
            text = timestamp,
            color = Color.White.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

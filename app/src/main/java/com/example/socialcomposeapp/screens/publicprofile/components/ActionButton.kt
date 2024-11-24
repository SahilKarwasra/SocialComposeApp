package com.example.socialcomposeapp.screens.publicprofile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ActionButtons(
    onClick: () -> Unit,
    isFollowing: () ->  Boolean,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xff692663))
        ) {
            Text(text = if (isFollowing()) "Unfollow" else "Follow", color = Color.White)
        }
        Button(
            onClick = { /* Message Action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFDF5FE))
        ) {
            Text(text = "Message", color = Color(color = 0xff440E3F))
        }
    }
}
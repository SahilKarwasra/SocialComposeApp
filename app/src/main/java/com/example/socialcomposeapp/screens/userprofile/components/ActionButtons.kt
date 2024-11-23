package com.example.socialcomposeapp.screens.userprofile.components

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
fun ActionButtons(onIconClick: () -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onIconClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xff692663))
        ) {
            Text(text = "Edit Profile", color = Color.White)
        }
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFDF5FE))
        ) {
            Text(text = "Share Profile", color = Color(color = 0xff440E3F))
        }
    }
}
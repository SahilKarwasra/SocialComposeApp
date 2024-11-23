package com.example.socialcomposeapp.screens.editprofile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfilePicWithEditIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(100.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFEFE6EB), CircleShape),
            tint = Color.Gray
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit Icon",
            modifier = Modifier
                .size(28.dp)
                .background(Color.White, CircleShape)
                .padding(4.dp)
                .align(Alignment.BottomEnd),
            tint = Color(0xFF4E004A)
        )
    }
}
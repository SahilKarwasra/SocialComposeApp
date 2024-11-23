package com.example.socialcomposeapp.screens.message.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.socialcomposeapp.data.model.UserModel

@Composable
fun ProfileItem(index: Int, user: UserModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(55.dp)
                .background(Color.White, shape = CircleShape)
        ) {
            if (index == 0) {
                // Display search icon for the first item
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(34.dp)
                )
            } else {
                // Display user profile picture
                Image(
                    painter = rememberAsyncImagePainter(model = user.profilePictureUrl),
                    contentDescription = user.displayName,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )


            }
        }
        Text(
            text = user.displayName ?: "User",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1
        )
    }
}

package com.example.socialcomposeapp.screens.userprofile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileHeader(
    username: String = "",
    profileImageUrl: String = ""
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = Color.LightGray,
            modifier = Modifier.size(100.dp)
        ) {
//            AsyncImage(
//                model = profileImageUrl,
//                contentDescription = "Profile Image",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = username,
            color = Color(0xffFDF5FE),
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}
package com.example.socialcomposeapp.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomBottomNavigationBar(
    onHomeClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onCenterClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(80.dp)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.BottomCenter// Transparent background for overall Box
    ) {
        Surface(
            color = Color.White,
            border = BorderStroke(2.dp, Color(0xff440E3F)),
            shape = RoundedCornerShape(34.dp),
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(60.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onHomeClick) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "Home",
                        tint = Color(0xFF692663),
                        modifier = Modifier.size(28.dp)
                    )
                }
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Notification",
                        tint = Color(0xFF692663),
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.width(60.dp))
                IconButton(onClick = onMessageClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Chat,
                        contentDescription = "Message",
                        tint = Color(0xFF692663),
                        modifier = Modifier.size(28.dp)
                    )
                }
                IconButton(onClick = onProfileClick) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Profile",
                        tint = Color(0xFF692663),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }

        Surface(
            shape = RoundedCornerShape(30.dp),
            border = BorderStroke(2.dp, Color(0xFFEFEFEF)),
            modifier = Modifier
                .size(64.dp)
                .offset(y = (-12).dp),
            color = Color.Transparent,
            tonalElevation = 0.dp
        ) {
            FloatingActionButton(
                onClick = { onCenterClick() },
                contentColor = Color(0xFF692663),
                modifier = Modifier.size(80.dp),
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(30.dp),
                    tint = Color(0xFF692663) // Icon color
                )
            }
        }
    }
}

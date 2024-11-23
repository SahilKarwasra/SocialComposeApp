package com.example.socialcomposeapp.screens.publicprofile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingMessage(
    onBackClick: () -> Unit = {},
    username: String = ""
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = username,
                    color = Color(0xffFDF5FE),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(color = 0xff692663),
            titleContentColor = Color(color = 0xffFDF5FE),
            actionIconContentColor = Color(color = 0xffFDF5FE),
            navigationIconContentColor = Color(color = 0xffFDF5FE),
            scrolledContainerColor = Color(color = 0xff440E3F)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) { // Add IconButton for navigation
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go Back"
                )
            }
        }
    )
}
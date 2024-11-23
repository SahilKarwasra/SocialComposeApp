package com.example.socialcomposeapp.screens.message.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingMessage(name: String) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = name,
                    color = Color(0xffFDF5FE).copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Your Message",
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
    )
}
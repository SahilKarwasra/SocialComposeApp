package com.example.socialcomposeapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.navigation.DestinationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
//    navigate: (DestinationScreen) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Social Compose",
                        color = Color(color = 0xffFDF5FE),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxSize()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(color = 0xff692663),
                    titleContentColor = Color(color = 0xffFDF5FE),
                    actionIconContentColor = Color(color = 0xffFDF5FE),
                    navigationIconContentColor = Color(color = 0xffFDF5FE),
                    scrolledContainerColor = Color(color = 0xff440E3F)
                ),
                modifier = Modifier.height(80.dp)
            )
        },

        bottomBar = {
            BottomAppBar(
                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween, // Ensures even spacing between icons
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.Home,
                                contentDescription = "Home"
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Chat,
                                contentDescription = "Home"
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.PersonOutline,
                                contentDescription = "Home"
                            )
                        }
                    }
                },
                containerColor = Color(color = 0xff692663),
                contentColor = Color(color = 0xfffdf5fe).copy(alpha = 0.5f),
                modifier = Modifier.height(80.dp)
            )
        }
    ) {
        Column(
            modifier
                .fillMaxSize()
                .padding(it)
                .background(Color(color = 0xff440E3F))
        ) {

        }
    }
}

@Preview
@Composable
private fun s() {
    HomeScreen()
}
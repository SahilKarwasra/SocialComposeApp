package com.example.socialcomposeapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.data.model.Post
import com.example.socialcomposeapp.navigation.DestinationScreen
import com.example.socialcomposeapp.screens.home.components.PostCard
import com.example.socialcomposeapp.screens.home.components.StoryRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigate: (DestinationScreen) -> Unit,
    posts: List<Post>
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
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
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(color = 0xff692663),
                    titleContentColor = Color(color = 0xffFDF5FE),
                    actionIconContentColor = Color(color = 0xffFDF5FE),
                    navigationIconContentColor = Color(color = 0xffFDF5FE),
                    scrolledContainerColor = Color(color = 0xff440E3F)
                ),
                actions = { // Add the actions parameter
                    IconButton(onClick = { /* Handle notification click */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications, // Use the notification icon
                            contentDescription = "Notifications"
                        )
                    }
                },
                modifier = Modifier.height(80.dp)
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(color = 0xff440E3F))
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                StoryRow()
            }
            items(posts) { post ->
                PostCard(
                    username = post.username,
                    timeAgo = post.timeAgo,
                    postText = post.postText
                )
            }
        }

    }
}




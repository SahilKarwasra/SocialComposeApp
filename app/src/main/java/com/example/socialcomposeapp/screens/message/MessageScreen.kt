package com.example.socialcomposeapp.screens.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.socialcomposeapp.R
import com.example.socialcomposeapp.data.model.Message
import com.example.socialcomposeapp.data.model.Profile
import com.example.socialcomposeapp.navigation.DestinationScreen
import com.example.socialcomposeapp.screens.components.CustomBottomNavigationBar
import com.example.socialcomposeapp.screens.message.component.GreetingMessage
import com.example.socialcomposeapp.screens.message.component.MessageList
import com.example.socialcomposeapp.screens.message.component.ProfileRow
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MessageScreen(
    navigate: (DestinationScreen) -> Unit,
    viewModel: MessageViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff440E3F))
    ) {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId != null) {
            LaunchedEffect(userId) {
                viewModel.getUserData(userId)
                viewModel.fetchAllUsers()
            }
        }

        val userName = viewModel.userName.collectAsState()
        val userProfiles = viewModel.users.collectAsState()
        val uiState = viewModel.uiState.collectAsState()

        when (uiState.value) {
            is MessageEvent.Loading -> {
                Text("Loading...", color = Color.White)
            }
            is MessageEvent.Error -> {
                Text(
                    text = (uiState.value as MessageEvent.Error).message,
                    color = Color.Red
                )
            }
            else -> {
                GreetingMessage("Hey ${userName.value},")
                Spacer(modifier = Modifier.height(24.dp))
                ProfileRow(userProfiles.value)
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.7f))
                Spacer(modifier = Modifier.height(24.dp))
                MessageList(users = userProfiles.value)
            }
        }

    }
    CustomBottomNavigationBar(
        onHomeClick = {
            navigate(DestinationScreen.HomeScreenObj)
        },
        onProfileClick = {
            navigate(
                DestinationScreen.ProfileScreenObj
            )
        },
        onSearchClick = {
            navigate(
                DestinationScreen.SearchScreenObj
            )
        }
    )
}


package com.example.socialcomposeapp.screens.publicprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.navigation.DestinationScreen
import com.example.socialcomposeapp.screens.publicprofile.components.ActionButtons
import com.example.socialcomposeapp.screens.publicprofile.components.BioSection
import com.example.socialcomposeapp.screens.publicprofile.components.GreetingMessage
import com.example.socialcomposeapp.screens.publicprofile.components.PostsSection
import com.example.socialcomposeapp.screens.publicprofile.components.StatItem
import com.example.socialcomposeapp.screens.publicprofile.components.UserProfileHeader
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel


@Composable
fun PublicProfilesScreen(
    navigate: (DestinationScreen) -> Unit,
    userId: String,
    viewModel: PublicProfileViewModel = koinViewModel(),
    onBackPress: () -> Unit
) {

    val userData by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        if(userData.user == null) {
            viewModel.fetchUser(userId)
        }
    }

    val isLoading by remember {
        derivedStateOf {
            userData.isLoading && userData.user == null
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff440E3F))
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            PublicProfileSubScreen(userId = userId, userData = viewModel.user, toggleFollow = viewModel::toggleFollow, onBackPress = onBackPress)
        }
    }


}

@Composable
fun PublicProfileSubScreen(
    userId: String,
    userData: StateFlow<UserDate>,
    toggleFollow: (String) -> Unit,
    onBackPress: () -> Unit
){
    val state by userData.collectAsState()

    val user by remember {
        derivedStateOf {
            state.user
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        user?.let { user ->
            GreetingMessage(
                onBackClick = onBackPress
            )

            Spacer(modifier = Modifier.height(8.dp))

            UserProfileHeader(username = user.displayName)

            BioSection(bio = user.bio)

            Spacer(modifier = Modifier.height(26.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(count =  user.postsCount  , label = "Posts")

                VerticalDivider(
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp),
                    color = Color.Gray.copy(alpha = 0.5f)
                )

                StatItem(count = user.followersCount , label = "Followers")

                VerticalDivider(
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp),
                    color = Color.Gray.copy(alpha = 0.5f)
                )
                StatItem(count =user.followingCount , label = "Following")
            }


            Spacer(modifier = Modifier.height(24.dp))

            ActionButtons(
                isFollowing = { state.isFollowed },
                onClick = { toggleFollow(userId) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            PostsSection()
        }
    }
}
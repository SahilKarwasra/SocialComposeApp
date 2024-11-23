package com.example.socialcomposeapp.screens.userprofile


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.socialcomposeapp.navigation.DestinationScreen
import com.example.socialcomposeapp.screens.components.CustomBottomNavigationBar
import com.example.socialcomposeapp.screens.userprofile.components.ActionButtons
import com.example.socialcomposeapp.screens.userprofile.components.BioSection
import com.example.socialcomposeapp.screens.userprofile.components.GreetingMessage
import com.example.socialcomposeapp.screens.userprofile.components.PostsSection
import com.example.socialcomposeapp.screens.userprofile.components.ProfileHeader
import com.example.socialcomposeapp.screens.userprofile.components.StatsRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    navigate: (DestinationScreen) -> Unit,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    val user = viewModel.user.collectAsStateWithLifecycle().value
    val isLoading = viewModel.isLoading.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff440E3F))
    ) {
        GreetingMessage()
        Spacer(modifier = Modifier.height(8.dp))

        ProfileHeader(
            username = user?.displayName ?: ""
        )

        BioSection(
            user?.bio ?: ""
        )

        Spacer(modifier = Modifier.height(26.dp))
        StatsRow(
            postCount = user?.postsCount ?: 0,
            followerCount = user?.followersCount ?: 0,
            followingCount = user?.followingCount ?: 0
        )
        Spacer(modifier = Modifier.height(24.dp))
        ActionButtons(
            onIconClick = {
                navigate(
                    DestinationScreen.EditProfileScreenObj
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        PostsSection()
    }
    CustomBottomNavigationBar(
        onMessageClick = {
            navigate(
                DestinationScreen.MessageScreenObj
            )
        },
        onHomeClick = {
            navigate(
                DestinationScreen.HomeScreenObj
            )
        },
        onSearchClick = {
            navigate(
                DestinationScreen.SearchScreenObj
            )
        }
    )
}




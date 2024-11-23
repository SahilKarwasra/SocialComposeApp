package com.example.socialcomposeapp.screens.publicprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.data.model.UserModel
import com.example.socialcomposeapp.data.repository.UserRepository
import com.example.socialcomposeapp.navigation.DestinationScreen
import com.example.socialcomposeapp.screens.components.CustomBottomNavigationBar
import com.example.socialcomposeapp.screens.publicprofile.components.ActionButtons
import com.example.socialcomposeapp.screens.publicprofile.components.BioSection
import com.example.socialcomposeapp.screens.publicprofile.components.GreetingMessage
import com.example.socialcomposeapp.screens.publicprofile.components.PostsSection
import com.example.socialcomposeapp.screens.publicprofile.components.StatsRow
import com.example.socialcomposeapp.screens.publicprofile.components.UserProfileHeader
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.compose.koinViewModel


@Composable
fun PublicProfilesScreen(
    navigate: (DestinationScreen) -> Unit,
    userId: String,
    viewModel: PublicProfileViewModel = koinViewModel()
) {
    val user = remember { mutableStateOf<UserModel?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val isFollowing = viewModel.isFollowing.value
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    // Fetch user details and following status
    LaunchedEffect(userId) {
        val fetchedUser = UserRepository().getUserById(userId)
        user.value = fetchedUser
        isLoading.value = false
        currentUserId?.let { viewModel.checkIfFollowing(it, userId) }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff440E3F))
    ) {

        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            user.value?.let { user ->
                GreetingMessage()
                Spacer(modifier = Modifier.height(8.dp))

                UserProfileHeader(username = user.displayName)
                BioSection(bio = user.bio)

                Spacer(modifier = Modifier.height(26.dp))
                StatsRow(
                    postCount = user.postsCount,
                    followerCount = user.followersCount,
                    followingCount = user.followingCount
                )

                Spacer(modifier = Modifier.height(24.dp))
                ActionButtons(
                    isFollowing = isFollowing,
                    onClick = {
                        currentUserId?.let { viewModel.toggleFollow(it, userId) }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))
                PostsSection() // Add user-specific post logic if needed
            }
        }
    }

    CustomBottomNavigationBar(
        onMessageClick = { navigate(DestinationScreen.MessageScreenObj) },
        onHomeClick = { navigate(DestinationScreen.HomeScreenObj) },
        onProfileClick = { navigate(DestinationScreen.ProfileScreenObj) },
        onSearchClick = { navigate(DestinationScreen.SearchScreenObj) }
    )
}
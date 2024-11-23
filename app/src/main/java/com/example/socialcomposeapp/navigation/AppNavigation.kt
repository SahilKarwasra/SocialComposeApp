package com.example.socialcomposeapp.navigation

import EditProfileScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialcomposeapp.data.model.Post
import com.example.socialcomposeapp.screens.userprofile.ProfileScreen
import com.example.socialcomposeapp.screens.login.LoginScreen
import com.example.socialcomposeapp.screens.home.HomeScreen
import com.example.socialcomposeapp.screens.message.MessageScreen
import com.example.socialcomposeapp.screens.publicprofile.PublicProfilesScreen
import com.example.socialcomposeapp.screens.search.SearchScreen
import com.example.socialcomposeapp.screens.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
        DestinationScreen.HomeScreenObj
    } else {
        DestinationScreen.LoginScreenObj
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<DestinationScreen.LoginScreenObj> {
            LoginScreen(
                navigate = { destination ->
                    when (destination) {
                        DestinationScreen.HomeScreenObj -> {
                            navController.navigate(DestinationScreen.HomeScreenObj) {
                                popUpTo(DestinationScreen.LoginScreenObj) {
                                    inclusive = true
                                }
                            }
                        }

                        else -> {
                            navController.navigate(destination)
                        }
                    }
                }

            )

        }
        composable<DestinationScreen.SignUpScreenObj> {
            SignUpScreen(
                navigate = { destination ->
                    when (destination) {
                        DestinationScreen.LoginScreenObj -> {
                            navController.navigate(DestinationScreen.LoginScreenObj) {
                                popUpTo(DestinationScreen.SignUpScreenObj) {
                                    inclusive = true
                                }
                            }
                        }

                        DestinationScreen.HomeScreenObj -> {
                            navController.navigate(DestinationScreen.HomeScreenObj) {
                                popUpTo(DestinationScreen.SignUpScreenObj) {
                                    inclusive = true
                                }
                            }
                        }

                        else -> {
                            navController.navigate(destination)
                        }

                    }
                }

            )
        }
        composable<DestinationScreen.HomeScreenObj> {
            HomeScreen(
                posts = samplePosts,
                navigate = {
                    navController.navigate(it)
                }
            )
        }
        composable<DestinationScreen.MessageScreenObj> {
            MessageScreen(
                navigate = {
                    navController.navigate(it)
                }
            )
        }
        composable<DestinationScreen.ProfileScreenObj> {
            ProfileScreen(
                navigate = {
                    navController.navigate(it)
                }
            )
        }
        composable<DestinationScreen.EditProfileScreenObj> {
            EditProfileScreen(
                navigate = {
                    navController.navigate(it)
                },
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<DestinationScreen.PublicProfileScreenObj> { backStackEntry ->
            val userId = (backStackEntry.arguments?.get("userId") as? String) ?: ""
            PublicProfilesScreen(
                navigate = { destination -> navController.navigate(destination) },
                userId = userId
            )
        }
        composable<DestinationScreen.SearchScreenObj> {
            SearchScreen(
                navigate = { destination -> navController.navigate(destination) }
            )
        }
    }
}

val samplePosts = listOf(
    Post(
        username = "Justin Bieber",
        timeAgo = "20 mins ago",
        postText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
    ),
    Post(
        username = "Taylor Swift",
        timeAgo = "1 hr ago",
        postText = "The industry has been blessed by the standards of typography."
    ),
    Post(
        username = "Ariana Grande",
        timeAgo = "2 hrs ago",
        postText = "Simply dummy text of the industry with amazing content."
    ),
    Post(
        username = "Ed Sheeran",
        timeAgo = "3 hrs ago",
        postText = "Incredible music text that’s used by industry standards."
    ),
    Post(
        username = "Billie Eilish",
        timeAgo = "5 hrs ago",
        postText = "Dummy text isn’t just dummy. It’s simply amazing!"
    )
)
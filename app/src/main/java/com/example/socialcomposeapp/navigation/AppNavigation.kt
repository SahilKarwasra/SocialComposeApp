package com.example.socialcomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialcomposeapp.screens.home.HomeScreen
import com.example.socialcomposeapp.screens.login.LoginScreen
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
                        DestinationScreen.SignUpScreenObj -> {
                            navController.navigate(DestinationScreen.SignUpScreenObj)
                        }

                        DestinationScreen.LoginScreenObj -> {}
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
                        DestinationScreen.SignUpScreenObj -> TODO()
                    }
                }

            )
        }
        composable<DestinationScreen.HomeScreenObj> {
            HomeScreen(
//                navigate = navController::navigate
            )
        }
    }
}
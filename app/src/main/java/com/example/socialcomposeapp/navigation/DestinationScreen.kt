package com.example.socialcomposeapp.navigation

import kotlinx.serialization.Serializable

sealed class DestinationScreen {
    @Serializable
    data object LoginScreenObj : DestinationScreen()
    @Serializable
    data object SignUpScreenObj : DestinationScreen()
    @Serializable
    data object HomeScreenObj : DestinationScreen()
}


package com.example.socialcomposeapp.navigation

import kotlinx.serialization.Serializable

sealed class DestinationScreen {
    @Serializable
    data object LoginScreenObj : DestinationScreen()
    @Serializable
    data object SignUpScreenObj : DestinationScreen()
    @Serializable
    data object HomeScreenObj : DestinationScreen()
    @Serializable
    data object MessageScreenObj : DestinationScreen()
    @Serializable
    data object ProfileScreenObj : DestinationScreen()
    @Serializable
    data object EditProfileScreenObj : DestinationScreen()
    @Serializable
    data class PublicProfileScreenObj(val userId: String) : DestinationScreen()
    @Serializable
    data object SearchScreenObj : DestinationScreen()


}


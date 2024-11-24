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

sealed class SubGraph {
    @Serializable
    data object Auth : SubGraph()
    @Serializable
    data object Home : SubGraph()

    @Serializable
    data object Message : SubGraph()

    @Serializable
    data object Search : SubGraph()

    @Serializable
    data object Profile : SubGraph()
}


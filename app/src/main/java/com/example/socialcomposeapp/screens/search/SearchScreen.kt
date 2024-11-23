package com.example.socialcomposeapp.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.navigation.DestinationScreen
import com.example.socialcomposeapp.screens.components.CustomBottomNavigationBar
import com.example.socialcomposeapp.screens.search.components.SearchResultItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchScreenViewModel = koinViewModel(),
    navigate: (DestinationScreen) -> Unit,
) {
    val searchQuery by searchViewModel.searchQuery.collectAsState()
    val searchResults by searchViewModel.searchResults.collectAsState()
    val isLoading by searchViewModel.isLoading.collectAsState()
    val errorMessage by searchViewModel.errorMessage.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar
        TextField(
            value = searchQuery,
            onValueChange = { query -> searchViewModel.updateSearchQuery(query) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search users...") },
            singleLine = true
        )

        // Loading Indicator
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        // Search Results List
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(searchResults) { user ->
                SearchResultItem(user = user, onClick = {
                    navigate(DestinationScreen.PublicProfileScreenObj(user.userId))
                })
            }
        }

        // Error Message
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
    CustomBottomNavigationBar(
        onHomeClick = {
            navigate(DestinationScreen.HomeScreenObj)
        },
        onProfileClick = {
            navigate(DestinationScreen.ProfileScreenObj)
        },
        onMessageClick = {
            navigate(DestinationScreen.MessageScreenObj)
        }
    )
}
package com.example.socialcomposeapp.screens.message.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.socialcomposeapp.data.model.UserModel

@Composable
fun ProfileRow(users: List<UserModel>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        // Render the first item as the search icon, and the rest as profile items
        itemsIndexed(users) { index, user ->
            ProfileItem(index, user)
        }
    }
}

package com.example.socialcomposeapp.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StoryRow() {
    val stories = listOf("My Story", "Varun", "Harsh", "Ajay") // Sample data

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp) // Spacing between items
    ) {
        itemsIndexed(stories) { index, story ->
            StoryItem(storyName = story, hasBorder = index != 0) // No border for the first item
        }
    }
}

@Composable
fun StoryItem(storyName: String, hasBorder: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.padding(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(80.dp) // Size of each circular item
                .background(Color(0xFFFFF0F8), shape = CircleShape) // Background color of the circle
                .then(
                    if (hasBorder) Modifier.border(
                        width = 4.dp,
                        color = Color(0xFFE88AE8), // Border color for the circle
                        shape = CircleShape
                    ) else Modifier
                )
        ) {
            // Add image or placeholder here
        }
        Spacer(modifier = Modifier.height(5.dp)) // Space between the circle and the text
        Text(
            text = storyName,
            color = Color(0xffFDF5FE),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Preview(showSystemUi = true)
@Composable
private fun s() {
    StoryRow()
}
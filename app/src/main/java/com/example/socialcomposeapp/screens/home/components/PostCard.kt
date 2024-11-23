package com.example.socialcomposeapp.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PostCard(
    username: String,
    timeAgo: String,
    postText: String
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFF0F8)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            Color(0xFFfaebfc),
                            shape = CircleShape
                        )
                        .border(1.dp, Color(0xFF692663), CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp)) // Space between image and text

                Column {
                    Text(
                        text = username,
                        color = Color(0xFF692663),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = timeAgo,
                        color = Color(0xFF692663).copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "Comment",
                    tint = Color(0xFF692663)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        Color(0xFFfaebfc),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(1.dp, Color(0xFF692663), RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(16.dp)) // Space between content and text

            Text(
                text = postText,
                color = Color(0xFF692663),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = Color(0xFF692663)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "4k",
                    color = Color(0xFF692663),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Comment,
                    contentDescription = "Comment",
                    tint = Color(0xFF692663)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "2k",
                    color = Color(0xFF692663),
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = "Comment",
                    tint = Color(0xFF692663)
                )
            }
        }
    }
}




@Preview
@Composable
fun PreviewPostCard() {
    PostCard(
        username = "Justin Bieber",
        timeAgo = "20 mins ago",
        postText = "20 mins Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text."
    )
}
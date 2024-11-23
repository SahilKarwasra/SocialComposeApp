package com.example.socialcomposeapp.screens.editprofile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ProfileDetailsCard(
    name: TextFieldValue,
    email: TextFieldValue,
    bio: TextFieldValue,
    onNameChange: (TextFieldValue) -> Unit,
    onEmailChange: (TextFieldValue) -> Unit,
    onBioChange: (TextFieldValue) -> Unit,
    onSaveClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8EAF4)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Name Field
            Text(
                text = "Name",
                color = Color(0xFF4E004A),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp),
            )
            CustomTextField(
                value = name,
                onValueChange = onNameChange,
                modifier = Modifier.padding(vertical = 8.dp),
            )

            // Email Field
            Text(
                text = "Email Address",
                color = Color(0xFF4E004A),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            )
            CustomTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Bio Field
            Text(
                text = "Bio",
                color = Color(0xFF4E004A),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            )
            CustomTextField(
                value = bio,
                onValueChange = onBioChange,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = 5
            )

            // Save Changes Button inside the card
            Button(
                onClick = onSaveClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp)
            ) {
                Text("Save Changes", color = Color(0xFF4E004A), fontWeight = FontWeight.Bold)
            }
        }
    }
}

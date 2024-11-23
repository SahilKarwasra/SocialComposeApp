package com.example.socialcomposeapp.screens.editprofile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun CustomTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    maxLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
            disabledContainerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
        ),
        maxLines = maxLines
    )
}

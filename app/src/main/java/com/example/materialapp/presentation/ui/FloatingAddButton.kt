package com.example.materialapp.presentation.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.materialapp.R

@Composable
fun FloatingAddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add), // Replace with your icon
            contentDescription = "Add",
            tint = Color.White
        )
    }
}

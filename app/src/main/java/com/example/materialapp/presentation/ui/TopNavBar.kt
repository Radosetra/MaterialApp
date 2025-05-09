package com.example.materialapp.presentation.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import com.example.materialapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo), // Replace with your logo
                    contentDescription = "Logo",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Materials", style = MaterialTheme.typography.titleMedium)
            }
        },
        actions = {

//            IconButton(onClick = { /* TODO: Handle click */ }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.add)
//                )
//            }
            Box(modifier = Modifier.padding(end = 16.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Logout",
                    tint = Color.Black
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
    )
}

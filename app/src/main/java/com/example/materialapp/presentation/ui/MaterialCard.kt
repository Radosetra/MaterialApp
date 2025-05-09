package com.example.materialapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.materialapp.data.model.Material
import com.example.materialapp.data.model.MaterialState

@Composable
fun MaterialCard(material: Material, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (material.state) {
                MaterialState.Good -> Color(0xFFE0F7FA)
                MaterialState.Damaged -> Color(0xFFFFF3E0)
                MaterialState.Unusable -> Color(0xFFFFEBEE)
            }
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ){
                Text(
                    text = "ID° ${material.id}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = material.design,
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = material.quantity.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
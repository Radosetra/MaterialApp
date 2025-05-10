package com.example.materialapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.materialapp.R
import com.example.materialapp.data.model.MaterialState
import com.example.materialapp.ui.theme.Damaged
import com.example.materialapp.ui.theme.DamagedLigth
import com.example.materialapp.ui.theme.Good
import com.example.materialapp.ui.theme.GoodLigth
import com.example.materialapp.ui.theme.Gray
import com.example.materialapp.ui.theme.Unusabel
import com.example.materialapp.ui.theme.UnusabelLigth

@Composable
fun Overview(
    stateCounts: Map<MaterialState, Int>
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
//    val tabTitles = listOf("Pie Chart", "Stat Cards")
    val proportions = listOf(
        MaterialState.Good to (stateCounts[MaterialState.Good] ?: 0),
        MaterialState.Damaged to (stateCounts[MaterialState.Damaged] ?: 0),
        MaterialState.Unusable to (stateCounts[MaterialState.Unusable] ?: 0)
    ).filter { it.second > 0 }

    val colors = mapOf(
        MaterialState.Good to Good, // Green
        MaterialState.Damaged to Damaged, // Orange
        MaterialState.Unusable to Unusabel // Red
    )

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Gray
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Overview",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.weight(1f))

                // Chart Icon (Tab 0)
                Box(
                    modifier = Modifier
                        .clickable { selectedTabIndex = 0 }
                        .padding(horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chart),
                        contentDescription = "Chart",
                        tint = if (selectedTabIndex == 0) Color.Black else Color.Gray,
                        modifier = Modifier.size(if (selectedTabIndex == 0) 28.dp else 24.dp)
                    )
                }

                // Vertical Separator
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .width(1.dp)
                        .background(Color.LightGray)
                )

                // Data Icon (Tab 1)
                Box(
                    modifier = Modifier
                        .clickable { selectedTabIndex = 1 }
                        .padding(horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.data),
                        contentDescription = "Data",
                        tint = if (selectedTabIndex == 1) Color.Black else Color.Gray,
                        modifier = Modifier.size(if (selectedTabIndex == 1) 28.dp else 24.dp)
                    )
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                when (selectedTabIndex) {
                    0 -> PieChartCanvas(proportions, colors)
                    1 -> StatCardList(proportions, colors)   // Replace with your card list composable
                }
            }
        }
    }
}
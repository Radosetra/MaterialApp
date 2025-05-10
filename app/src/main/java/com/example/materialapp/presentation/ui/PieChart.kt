package com.example.materialapp.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import com.example.materialapp.data.model.MaterialState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun PieChartCanvas(proportions:  List<Pair<MaterialState, Int>>, colors:  Map<MaterialState, Color>, modifier: Modifier = Modifier) {
    val total = proportions.sumOf { it.second }

    val _proportions = proportions.filter { it.second > 0 }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically // Align vertically
    ) {
        Canvas(
            modifier = Modifier
                .size(100.dp) // Adjust pie size here
                .align(Alignment.CenterVertically) // Make sure it aligns properly in the row
        ) {
            var startAngle = -90f
            for ((state, count) in _proportions) {
                val sweepAngle = (count.toFloat() / total.toFloat()) * 360f

                drawArc(
                    color = colors[state] ?: Color.Gray,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = 40f)
                )
                startAngle += sweepAngle
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Legend
        Column(modifier = Modifier.padding(start = 8.dp)) {
            _proportions.forEach { (state, count) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(colors[state] ?: Color.Gray, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(state.name)
                    Spacer(modifier = Modifier.weight(1f))
                    Text("$count")
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

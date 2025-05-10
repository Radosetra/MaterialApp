package com.example.materialapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.materialapp.data.model.MaterialState
import com.example.materialapp.ui.theme.Damaged
import com.example.materialapp.ui.theme.Good
import com.example.materialapp.ui.theme.Unusabel

@Composable
fun StatCardList(proportions:  List<Pair<MaterialState, Int>>, colors:  Map<MaterialState, Color>){

    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for ((state, count) in proportions) {
            StatCard(title = state.name, count = count, color = colors[state]!!, modifier = Modifier.weight(1f))
        }
    }
}
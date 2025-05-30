package com.example.materialapp.presentation.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.materialapp.data.model.Material

import com.example.materialapp.presentation.viewmodel.MaterialViewModel

@Composable
fun MaterialList(
    viewModel: MaterialViewModel,
    onMaterialClick: (Material) -> Unit
    ) {
    val materials by viewModel.materials.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(materials) { material ->
            MaterialCard(material = material, onClick = { onMaterialClick(material) })
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
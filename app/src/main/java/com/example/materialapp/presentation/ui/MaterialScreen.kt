package com.example.materialapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.materialapp.presentation.viewmodel.MaterialViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.materialapp.R
import com.example.materialapp.data.model.Material

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialScreen(viewModel: MaterialViewModel){
    var showDialog by remember { mutableStateOf(false) }
    var selectedMaterial by remember { mutableStateOf<Material?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    val stateCounts by viewModel.stats.collectAsState()

    if (showSheet && selectedMaterial != null) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false }
        ) {
            Box(modifier = Modifier.padding(24.dp)){
                Column {
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                    ){
                        Text(
                            text = "ID°",
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${selectedMaterial!!.id}",
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                    ){
                        Text(
                            text = "Design",
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = selectedMaterial!!.design,
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                    ){
                        Text(
                            text = "Quantity",
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${selectedMaterial!!.quantity}",
                            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            text = "Delete",
                            onClick = { viewModel.deleteMaterial(selectedMaterial!!.id) },
                            backgroundColor = Color(0xFFFECDCD),
                            iconRes = R.drawable.trash
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            text = "Edit",
                            onClick = { showDialog = true },
                            backgroundColor = Color(0xFF4D67FF),
                            iconRes = R.drawable.edit_pencil
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopNavBar()
        },
        floatingActionButton = {
            FloatingAddButton { showDialog = true }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)){
            Column(modifier = Modifier
                .fillMaxSize()) {
//                PieChartCanvas(stateCounts)
//                StatCardList(stateCounts)
                Overview(stateCounts = stateCounts)
                MaterialList(
                    viewModel = viewModel,
                    onMaterialClick = {
                        selectedMaterial = it
                        showSheet = true
                    }
                )
            }
        }

        if (showDialog) {
            DialogForm(
                selectedMaterial = selectedMaterial,
                onDismiss = { showDialog = false },
                onConfirm = { material ->
                    if (selectedMaterial == null) {
                        // Create
                        viewModel.addMaterial(material)
                    } else {
                        // Update
                        viewModel.updateMaterial(selectedMaterial!!.id, material)
                    }
                    showDialog = false
                }
            )
        }
    }
}

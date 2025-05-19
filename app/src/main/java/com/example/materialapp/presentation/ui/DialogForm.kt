package com.example.materialapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.materialapp.R
import com.example.materialapp.data.model.Material
import com.example.materialapp.data.model.MaterialState

@Composable
fun DialogForm(
    selectedMaterial: Material? = null,
    onDismiss: () -> Unit,
    onConfirm: (material: Material) -> Unit
) {
    var _designation by remember { mutableStateOf(selectedMaterial?.design ?: "") }
    var _quantity by remember { mutableStateOf(selectedMaterial?.quantity?.toString() ?: "0") }
    var _state by remember { mutableStateOf(selectedMaterial?.state ?: MaterialState.Good) }
    var expanded by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (selectedMaterial == null) "Add Material" else "Update Material") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                TextField(
                    value = _designation,
                    onValueChange = { _designation = it },
                    label = { Text("Designation") }
                )
                TextField(
                    value = _quantity,
                    onValueChange = { _quantity = it },
                    label = { Text("Quantity") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Box {
                    OutlinedTextField(
                        value = _state.name,
                        onValueChange = {},
                        label = { Text("State") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true }
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        MaterialState.values().forEach { state ->
                            DropdownMenuItem(
                                onClick = {
                                    _state = state
                                    expanded = false
                                },
                                text = { Text(state.name) }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                text = "Confirm",
                onClick = {
                    val id = selectedMaterial?.id ?: 0
                    val material = Material(
                        id = id,
                        design = _designation,
                        quantity = _quantity.toIntOrNull() ?: 0,
                        state = _state
                    )
                    onConfirm(material)
                },
                backgroundColor = Color(0xFF4D67FF),
                iconRes = R.drawable.check
            )
        },
        dismissButton = {
            Button(
                text = "Cancel",
                onClick = onDismiss,
                iconRes = R.drawable.close,
                contentColor = Color(0xFF4D67FF),
                isOutlined = true
            )
        }
    )
}

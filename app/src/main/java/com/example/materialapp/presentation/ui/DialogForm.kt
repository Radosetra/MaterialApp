package com.example.materialapp.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.materialapp.R

@Composable
fun DialogForm(
    designation: String = "",
    quantity: String = "0",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var _designation by remember { mutableStateOf(designation) }
    var _quantity by remember { mutableStateOf(quantity) }

    AlertDialog(

        onDismissRequest = onDismiss,
        title = { Text("Add Material") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = _designation,
                    onValueChange = { _designation = it },
                    label = "Designation"
                )
                TextField(
                    value = quantity,
                    onValueChange = { _quantity = it },
                    label = "Quantity",
                    keyboardType = KeyboardType.Number
                )
            }
        },
        confirmButton = {
            Button(
                text = "Confirm",
                onClick = onConfirm,
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

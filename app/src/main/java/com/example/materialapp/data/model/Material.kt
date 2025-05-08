package com.example.materialapp.data.model

data class Material(
    val id: Int,
    val design: String,
    val state: MaterialState,
    val quantity: Int
)

enum class MaterialState {
    Good, Damaged, Unusable
}

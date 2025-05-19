package com.example.materialapp.data.repository

import com.example.materialapp.data.model.Material
import com.example.materialapp.data.remote.MaterialApiService
//import com.example.materialapp.data.model.MaterialState
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flowOf

//interface IMaterialRepository {
//    fun getAllMaterials(): Flow<List<Material>>
//}
//
//class MaterialRepository : IMaterialRepository {
//    override fun getAllMaterials(): Flow<List<Material>> {
//        return flowOf(
//            listOf(
//                Material(1, "Hammer", MaterialState.Good, 10),
//                Material(2, "Brick", MaterialState.Damaged, 5),
//                Material(3, "Truck", MaterialState.Unusable, 9),
//                Material(4, "Nail", MaterialState.Good, 20)
//            )
//        )
//    }
//}

class MaterialRepository(private val api: MaterialApiService) {

    suspend fun getAllMaterials() = api.getAllMaterials()
    suspend fun getMaterialById(id: Int) = api.getMaterialById(id)
    suspend fun createMaterial(material: Material) = api.createMaterial(material)
    suspend fun updateMaterial(id: Int, material: Material) = api.updateMaterial(id, material)
    suspend fun deleteMaterial(id: Int) = api.deleteMaterial(id)
}
package com.example.materialapp.data.remote

import com.example.materialapp.data.model.Material
import retrofit2.http.*

interface MaterialApiService {

    @GET("materials")
    suspend fun getAllMaterials(): List<Material>

    @GET("materials/{id}")
    suspend fun getMaterialById(@Path("id") id: Int): Material

    @POST("materials")
    suspend fun createMaterial(@Body material: Material): Material

    @PUT("materials/{id}")
    suspend fun updateMaterial(@Path("id") id: Int, @Body material: Material): Material

    @DELETE("materials/{id}")
    suspend fun deleteMaterial(@Path("id") id: Int): Unit
}

package com.example.materialapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.materialapp.data.model.Material
//import com.example.materialapp.data.repository.IMaterialRepository
import com.example.materialapp.data.repository.MaterialRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//class MaterialViewModel(
//    private val repository: IMaterialRepository
//) : ViewModel() {
//
//    private val _materialList = MutableStateFlow<List<Material>>(emptyList())
//    val materialList: StateFlow<List<Material>> = _materialList
//
//    val stats = _materialList.map { list ->
//        list.groupingBy { it.state }.eachCount()
//    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())
//
//
//    init {
//        loadMaterials()
//    }
//
//    private fun loadMaterials() {
//        viewModelScope.launch {
//            repository.getAllMaterials().collect { list ->
//                _materialList.value = list
//            }
//        }
//    }
//
//}

class MaterialViewModel : ViewModel() {
    private val repository = MaterialRepository(RetrofitClient.materialService)

    private val _materials = MutableStateFlow<List<Material>>(emptyList())
    val materials: StateFlow<List<Material>> = _materials

    private val _statusMessage = MutableStateFlow<String?>(null)
    val statusMessage: StateFlow<String?> = _statusMessage

    val stats = _materials.map { list ->
        list.groupingBy { it.state }.eachCount()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    init {
        loadMaterials()
    }

    fun loadMaterials() {
        viewModelScope.launch {
            try {
                val result = repository.getAllMaterials()
                _materials.value = result
                _statusMessage.value = "Materials loaded successfully."
            } catch (e: Exception) {
                _statusMessage.value = "Error fetching materials: ${e.localizedMessage}"
            }
        }
    }

    fun addMaterial(material: Material) {
        viewModelScope.launch {
            try {
                repository.createMaterial(material)
                loadMaterials() // Refresh list
                _statusMessage.value = "Material added successfully."
            } catch (e: Exception) {
                _statusMessage.value = "Error adding material: ${e.localizedMessage}"
            }
        }
    }

    fun updateMaterial(id: Int, material: Material) {
        viewModelScope.launch {
            try {
                repository.updateMaterial(id, material)
                loadMaterials()
                _statusMessage.value = "Material updated successfully."
            } catch (e: Exception) {
                _statusMessage.value = "Error updating material: ${e.localizedMessage}"
            }
        }
    }

    fun deleteMaterial(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteMaterial(id)
                loadMaterials()
                _statusMessage.value = "Material deleted successfully."
            } catch (e: Exception) {
                _statusMessage.value = "Error deleting material: ${e.localizedMessage}"
            }
        }
    }
}

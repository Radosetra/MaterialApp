package com.example.materialapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.materialapp.data.model.Material
import com.example.materialapp.data.repository.IMaterialRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MaterialViewModel(
    private val repository: IMaterialRepository
) : ViewModel() {

    private val _materialList = MutableStateFlow<List<Material>>(emptyList())
    val materialList: StateFlow<List<Material>> = _materialList

    val stats = _materialList.map { list ->
        list.groupingBy { it.state }.eachCount()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyMap())

    init {
        loadMaterials()
    }

    private fun loadMaterials() {
        viewModelScope.launch {
            repository.getAllMaterials().collect { list ->
                _materialList.value = list
            }
        }
    }
}

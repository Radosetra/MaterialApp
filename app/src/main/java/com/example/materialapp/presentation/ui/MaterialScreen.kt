package com.example.materialapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.materialapp.presentation.viewmodel.MaterialViewModel

@Composable
fun MaterialScreen(viewModel: MaterialViewModel){
//    Retrieve the list of Material and Stats
//    val materiels by viewModel.materielList.collectAsState()
//    val stats by viewModel.stats.collectAsState()
//
    Scaffold() { padding ->
//        Column(modifier = Modifier.padding(padding)) {
//            StatsOverview(stats)
//            Spacer(Modifier.height(8.dp))
//            LazyColumn {
//                items(materiels) { materiel ->
//                    MaterielCard(materiel)
//                }
//            }
//        }
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            MaterialList(viewModel)
        }
    }
}

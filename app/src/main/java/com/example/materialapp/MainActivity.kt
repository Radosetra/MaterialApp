package com.example.materialapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.materialapp.data.repository.MaterialRepository
import com.example.materialapp.presentation.ui.MaterialScreen
import com.example.materialapp.presentation.viewmodel.MaterialViewModel
import com.example.materialapp.ui.theme.MaterialAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the repository manually
        val repository = MaterialRepository()

        // Create the ViewModel using the factory

        val viewModel = MaterialViewModel(repository)

        setContent {
            MaterialTheme {
                MaterialScreen(viewModel) // Pass ViewModel manually
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialAppTheme {
        Greeting("Android")
    }
}
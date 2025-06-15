package com.example.randomfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.randomfact.presentation.FactViewModel
import com.example.randomfact.ui.screens.FactScreen
import com.example.randomfact.ui.theme.RandomFactTheme

class MainActivity : ComponentActivity() {

    private val viewModel: FactViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomFactTheme {
                FactScreen(viewModel)
            }
        }
    }
}

package com.example.randomfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.randomfact.ui.screens.MainScreen
import com.example.randomfact.ui.theme.RandomFactTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomFactTheme {
                MainScreen()
            }
        }
    }
}

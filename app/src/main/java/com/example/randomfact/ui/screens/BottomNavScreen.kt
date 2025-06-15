package com.example.randomfact.ui.screens

sealed class BottomNavScreen(val route: String, val title: String) {
    object Fact : BottomNavScreen("fact", "Факт")
    object Favorites : BottomNavScreen("favorites", "Избранное")
}
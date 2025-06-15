package com.example.randomfact.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    // Навигационный контроллер
    val navController = rememberNavController()

    // Обёртка Scaffold для BottomNav
    Scaffold(
        bottomBar = {
            NavigationBar {
                // Слушаем текущее состояние навигации
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                // Список всех элементов BottomNav
                val screens = listOf(
                    BottomNavScreen.Fact,
                    BottomNavScreen.Favorites
                )

                screens.forEach { screen ->
                    NavigationBarItem(
                        label = { Text(screen.title) },
                        icon = {
                            Icon(
                                imageVector = when (screen) {
                                    BottomNavScreen.Fact -> Icons.Default.Home
                                    BottomNavScreen.Favorites -> Icons.Default.Favorite
                                },
                                contentDescription = screen.title
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Не создаём кучу копий в BackStack
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost описывает, какие экраны показывать
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.Fact.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Fact.route) {
                FactScreen(viewModel = koinViewModel())  // Твоя функция FactScreen с Koin ViewModel
            }
            composable(BottomNavScreen.Favorites.route) {
                FavoritesScreen(viewModel = koinViewModel()) // Твоя функция FavoritesScreen с Koin ViewModel
            }
        }
    }
}


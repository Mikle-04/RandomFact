package com.example.randomfact.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.randomfact.presentation.FactViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    viewModel: FactViewModel = koinViewModel()  // Получаем ViewModel через Koin
) {
    // Подписываемся на Flow всех избранных фактов
    val favorites by viewModel.favourites.collectAsState()

    // Оборачиваем всё в Surface для фона
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        // Основная вертикальная колонка
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),  // Отступы от краёв
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Заголовок экрана
            Text(
                text = "Избранные факты",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Если избранных нет — показываем заглушку
            if (favorites.isEmpty()) {
                Text("Пока нет избранных фактов")
            } else {
                // LazyColumn для списка избранных
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    // Для каждого факта — отдельный элемент
                    items(favorites) { fact ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            // Текст факта
                            Text(text = fact.text)

                            Spacer(modifier = Modifier.height(4.dp))

                            // Кнопка для удаления этого факта
                            Button(
                                onClick = {
                                    fact.id?.let { viewModel.deleteFavorite(it) }
                                }
                            ) {
                                Text("Удалить")
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка для очистки всего избранного
                Button(onClick = { viewModel.clearFavorites() }) {
                    Text("Очистить всё избранное")
                }
            }
        }
    }
}

package com.example.randomfact.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.randomfact.presentation.FactViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun FactScreen(viewModel: FactViewModel) {

    // Подписываемся на поток факта из ViewModel и превращаем его в Compose State
    val fact by viewModel.fact.collectAsState()

    // Подписываемся на поток загрузки из ViewModel
    val isLoading by viewModel.isLoading.collectAsState()

    // Подписываемся на поток ошибок из ViewModel
    val errorMessage by viewModel.errorMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Показываем Snackbar, если есть ошибка
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = it,
                    actionLabel = "Закрыть"
                )
            }
        }
    }

    // LaunchedEffect(Unit) гарантирует, что при первом рендере экрана вызывается loadFact()
    LaunchedEffect(Unit) {
        viewModel.loadFact()
    }

    // Surface — базовый контейнер с фоном, занимает весь экран
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        // Column — вертикальный контейнер для расположения дочерних элементов
        Column(
            modifier = Modifier
                .fillMaxSize()          // Занимает весь размер экрана
                .padding(16.dp),        // Отступы внутри Column
            verticalArrangement = Arrangement.Center, // Выравнивание содержимого по вертикали — центр
            horizontalAlignment = Alignment.CenterHorizontally // Выравнивание по горизонтали — центр
        ) {
            // when {} — обрабатываем три состояния: загрузка, ошибка или отображение факта
            when {
                // Если идёт загрузка — показываем индикатор загрузки
                isLoading -> {
                    CircularProgressIndicator()
                }
                // Если есть ошибка — показываем текст ошибки и кнопку "Повторить"
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "Ошибка", // Если ошибка есть — показываем, иначе "Ошибка"
                        color = MaterialTheme.colorScheme.error // Красный цвет ошибки из темы
                    )
                    // Отступ между текстом и кнопкой
                    Spacer(modifier = Modifier.height(20.dp))
                    // Кнопка для повторной загрузки факта
                    Button(onClick = { viewModel.loadFact() }) {
                        Text("Повторить")
                    }
                }
                // Если ни загрузка, ни ошибка — показываем сам факт и кнопку получить новый
                else -> {
                    Text(
                        text = fact?.text ?: "Нажми кнопку чтобы получить факт", // Текст факта или заглушка
                        style = MaterialTheme.typography.headlineMedium // Стиль заголовка из темы
                    )
                    // Отступ между текстом и кнопкой
                    Spacer(modifier = Modifier.height(20.dp))
                    // Кнопка для загрузки нового факта
                    Button(onClick = { viewModel.loadFact() }) {
                        Text("Получить новый факт")
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    // Кнопка: добавить в избранное
                    Button(
                        onClick = { viewModel.addToFavorites() },
                        enabled = fact != null // Чтобы не нажать, если факт пустой
                    ) {
                        Text("Добавить в избранное")
                    }
                }
            }
        }
    }
}
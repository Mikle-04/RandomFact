package com.example.randomfact.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomfact.domain.model.Fact
import com.example.randomfact.domain.repository.FactRepository
import com.example.randomfact.domain.usecase.GetRandomFactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FactViewModel(
    private val getRandomFactUseCase: GetRandomFactUseCase,
    private val repository: FactRepository
) : ViewModel() {
    // Текущее состояние факта
    private val _fact = MutableStateFlow<Fact?>(null)
    val fact: StateFlow<Fact?> = _fact

    // Текущее состояние загрузки
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Текущая ошибка (если есть)
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Flow со всеми избранными фактами
    val favourites =
        repository.getFavorites().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //Загружает новый случайный факт.
    fun loadFact() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _fact.value = getRandomFactUseCase()
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Ошибка загрузки: ${
                    e.localizedMessage ?: "Неизвестная ошибка"
                }"
            } finally {
                _isLoading.value = false
            }
        }

    }

    // Добавляем текущий факт в избранное
    fun addToFavorites() {
        viewModelScope.launch {
            fact.value?.let {
                repository.addToFavorites(it)
            }
        }
    }

    // Удаляем факт из избранного по ID
    fun deleteFavorite(id: Int) {
        viewModelScope.launch {
            repository.deleteFavorite(id)
        }
    }

    // Полностью очищаем избранное
    fun clearFavorites() {
        viewModelScope.launch {
            repository.clearFavorites()
        }
    }
}
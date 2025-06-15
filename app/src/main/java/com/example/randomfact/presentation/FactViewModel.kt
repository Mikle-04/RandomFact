package com.example.randomfact.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomfact.domain.model.Fact
import com.example.randomfact.domain.usecase.GetRandomFactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FactViewModel(
    private val getRandomFactUseCase: GetRandomFactUseCase
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
            }finally {
                _isLoading.value = false
            }
        }

    }
}